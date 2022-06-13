package com.servlet;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentMain implements ClassFileTransformer {
	// 在应用启动前调用
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("com.servlet.AgentMain:v2.0");
		inst.addTransformer(new AgentMain());
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		//className转换，javax/servlet/http/HttpServlet转换为javax.servlet.http.HttpServlet
		className = className.replaceAll("/",".");
		String target = "javax.servlet.http.HttpServlet";
		if (target.equals(className)) {
			System.out.println("ClassLoader:"+loader+" className:"+className);
			try {
				return buildClass(target, loader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public byte[] buildClass(String target, ClassLoader loader)
			throws NotFoundException, IOException, CannotCompileException {
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cla = pool.get(target);
		CtClass[] params = new CtClass[] {
				pool.get("javax.servlet.http.HttpServletRequest"),
				pool.get("javax.servlet.http.HttpServletResponse")
		};
		//获取service方法
		CtMethod method = cla.getDeclaredMethod("service", params);
		//在service方法之前插入begin， 可能会ClassNotFound
		method.insertBefore("com.servlet.DispatcherServletCollect.begin($args);");
		//这里需要将DispatcherServletCollecct加载到类加载器（tomcat的类加载器），不然DispatcherServletCollecct找不到HttpServletRequest
//		pool.get("com.servlet.DispatcherServletCollect").toClass(loader,null);
		System.out.println("DispatcherServletCollecct加载成功！");
		return cla.toBytecode();

	}
}
