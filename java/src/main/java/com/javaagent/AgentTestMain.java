package com.javaagent;

/**
 * @author 陈孟飞
 * @date 2021/8/27
 */
public class AgentTestMain {
    /**
     * 在VM options中增加：-javaagent:D:\IdeaProjects\demo\apm-javaagent\target\apm-javaagent-0.0.1-SNAPSHOT.jar
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("execute main");
    }
}
