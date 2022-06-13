package com.controller.collects;

import com.controller.AbstractCollects;
import com.controller.AgentLoader;
import com.controller.Collect;
import javassist.*;
/**
 * spring 注解服务收集
 * Created by tommy on 17/7/14.
 */
public class SpringServiceCollects extends AbstractCollects implements Collect {

    public static SpringServiceCollects INSTANCE = new SpringServiceCollects();

    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    //编写src
    static {
        //begin的src
        StringBuilder sbuilder = new StringBuilder();
        String className = SpringServiceCollects.class.getName();
        String statisticsName = Statistics.class.getName();
        sbuilder.append("System.out.println(\"javassist begin...\");");
        sbuilder.append(className);
        sbuilder.append(" instance= ");
        sbuilder.append(className);
        sbuilder.append(".INSTANCE;\r\n");
        //调用begin方法，封装时间和其他属性为Statistics对象并返回，
        sbuilder.append(statisticsName);
        //begin的两个入参是className与methodName，这里无法得到两个参数的值，所以使用占位符，在最终将src放入body的时候接入
        sbuilder.append(" statistic =instance.begin(\"%s\",\"%s\");");
        beginSrc = sbuilder.toString();

        //end的src
        sbuilder = new StringBuilder();
        sbuilder.append("System.out.println(\"javassist end...\");");
        //调用父类的end方法，即：计算响应时间
        sbuilder.append("instance.end(statistic);");
        endSrc = sbuilder.toString();

        //error的src
        sbuilder = new StringBuilder();
        sbuilder.append("System.out.println(\"javassist error...\");");
        sbuilder.append("instance.error(statistic,e);");
        errorSrc = sbuilder.toString();
    }

    @Override
    public boolean isTarget(String className, ClassLoader loader, CtClass ctclass) {
        try {
            //获取类的注解，即@Service注解标注的类都是需要监听的
            for (Object obj : ctclass.getAnnotations()) {
                if (obj.toString().startsWith("@org.springframework.stereotype.Service")) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println(String.format("bit apm run error targetClassName=%s errorMessage=%s",className,e.getClass().getSimpleName()+":"+e.getMessage()));
        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass targetClass) throws Exception {
        AgentLoader agentLoader = new AgentLoader(className,loader, targetClass);
        //采集公共的、非静态、非本地的方法
        //只采集本来的方法，不采集父类
        for(CtMethod targetMethod : targetClass.getDeclaredMethods()){
            //非public
            if(!Modifier.isPublic(targetMethod.getModifiers())){
                continue;
            }
            //静态
            if(Modifier.isStatic(targetMethod.getModifiers())){
                continue;
            }
            //本地
            if(Modifier.isNative(targetMethod.getModifiers())){
                continue;
            }
            //封装逻辑
            AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
            /**
             * 注意：这里最终也会封装成一个最终src，然后调用setBody
             * 并没有调用insertBefore(beginSrc)、insertAfter(endSrc)等方法
             * 所以，begin中定义的变量在后面的代码块是可以访问的，本质是同一代码块
             */
            build.setBeginSrc(String.format(beginSrc, className, targetMethod.getName()));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);
            //复制原方法为代理方法，重新原来方法为新方法
            agentLoader.updateMethod(targetMethod, build);
        }
        return agentLoader.toBytecote();
    }

    /**
     * 织入的代码
     * @param stat
     */
    @Override
    public void sendStatistics(Statistics stat) {
        sendStatisticByHttp(stat,"serviceLog");
    }

    @Override
    public Statistics begin(String className, String method) {
        //super.begin主要是定义时间变量并封装成对象，供end和error使用
        ServiceStatistics serviceStatistics = new ServiceStatistics(super.begin(className, method));
        serviceStatistics.serviceName = className;
        serviceStatistics.methodName = method;
        serviceStatistics.logType="service";
        return serviceStatistics;
    }

    /**
     * 需要使用到的字段
     */
    public static class ServiceStatistics extends Statistics {
        public String serviceName; //服务名称
        public String methodName;// 方法名称
        public ServiceStatistics(Statistics s) {
            super(s);
        }
    }
}
