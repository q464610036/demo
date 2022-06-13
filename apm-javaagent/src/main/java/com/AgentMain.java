package com;

import java.lang.instrument.Instrumentation;

/**
 * @author 陈孟飞
 * @date 2021/8/27
 */
public class AgentMain {
    public static void premain(String args, Instrumentation inst) {
        System.out.println("execute premain1111");
    }
}
