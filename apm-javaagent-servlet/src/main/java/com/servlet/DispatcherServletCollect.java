package com.servlet;


import javax.servlet.http.HttpServletRequest;

public class DispatcherServletCollect {
	public static void begin(Object params[]) {
		System.out.println("agent begin..."+Thread.currentThread().getContextClassLoader().toString());
		HttpServletRequest request = (HttpServletRequest) params[0];
		System.out.println("远程地址是："+request.getRequestURI());
	}

}
