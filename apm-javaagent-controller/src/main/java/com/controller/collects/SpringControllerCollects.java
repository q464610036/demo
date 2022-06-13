package com.controller.collects;

import com.controller.AbstractCollects;
import com.controller.AgentLoader;
import com.controller.Collect;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * @author 陈孟飞
 * @date 2021/9/2
 */
public class SpringControllerCollects extends AbstractCollects implements Collect {

    public static SpringControllerCollects INSTANCE = new SpringControllerCollects();

    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    private String rootRequestUrl = "";

    //编写src
    static {
        //begin的src
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("System.out.println(\"javassist begin...\");");
        sbuilder.append(" com.controller.collects.SpringControllerCollects instance= " +
                "com.controller.collects.SpringControllerCollects.INSTANCE;\r\n");
        //调用begin方法，封装时间和其他属性为Statistics对象并返回，
        sbuilder.append(" com.controller.collects.SpringControllerCollects.WebStatistics statistic =" +
                "(com.controller.collects.SpringControllerCollects.WebStatistics)instance.begin(\"%s\",\"%s\");");
        sbuilder.append(" statistic.urlAddress=\"%s\";");
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
                // 通过正则表达示计算出RequestMapping 地址
                if (obj.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping")) {
                    rootRequestUrl = getAnnotationValue("value", obj.toString());
                } else if (obj.toString().startsWith("@org.springframework.stereotype.Controller")) {
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
            //有requestMapping注解
            String requestUrl = getRequestMappingValue(targetMethod);
            if (requestUrl == null) {
                continue;
            }
            //封装逻辑
            AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
            /**
             * 注意：这里最终也会封装成一个最终src，然后调用setBody
             * 并没有调用insertBefore(beginSrc)、insertAfter(endSrc)等方法
             * 所以，begin中定义的变量在后面的代码块是可以访问的，本质是同一代码块
             */
            build.setBeginSrc(String.format(beginSrc, className, targetMethod.getName(), rootRequestUrl + requestUrl));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);
            //复制原方法为代理方法，重新原来方法为新方法
            agentLoader.updateMethod(targetMethod, build);
        }
        return agentLoader.toBytecote();
    }

    @Override
    public Statistics begin(String className, String method) {
        WebStatistics webStat = new WebStatistics(super.begin(className, method));
        webStat.controlName = className;
        webStat.methodName = method;
        webStat.logType="web";
        return  webStat;
    }

    /**
     * 获取接口url
     * @param m
     * @return
     * @throws ClassNotFoundException
     */
    private String getRequestMappingValue(CtMethod m) throws ClassNotFoundException {
        for (Object s : m.getAnnotations()) {
            if (s.toString().startsWith("@org.springframework.web.bind.annotation.RequestMapping")) {
                String val = getAnnotationValue("value", s.toString());
                return val==null?"/":val;
            }
        }
        return null;
    }

    @Override
    public void sendStatistics(Statistics stat) {
        sendStatisticByHttp(stat,"webLog");
    }

    public static class WebStatistics extends Statistics {
        public String urlAddress; //url 地址
        public String controlName; //服务名称
        public String methodName;// 方法名称
        public WebStatistics(Statistics s) {
            super(s);
        }
    }
}
