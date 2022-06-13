package com.controller.collects;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * Created by tommy on 17/7/17.
 */
public class JdbcCollectTest    {

    @Test
    public void isTarget() throws NotFoundException {
        JdbcCommonCollects ins = JdbcCommonCollects.INSTANCE;
        String classname = "com.mysql.jdbc.NonRegisteringDriver";
        ClassLoader loader = JdbcCommonCollects.class.getClassLoader();
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass cclass = pool.get(classname);
        Assert.isTrue(ins.isTarget(classname, loader, cclass));
    }

    @Test
    public void proxyConnectionTest() throws SQLException, ClassNotFoundException {
        JdbcCommonCollects ins = JdbcCommonCollects.INSTANCE;
        Connection conn = ins.proxyConnection(getConnection());
        PreparedStatement ps = conn.prepareStatement("select * FROM city");
        ps.execute();
    }

    @Test
    public void transform() throws Exception {
    	
    	System.setProperty("$bit_server", "http://123.56.21.219:8860/receive");
		System.setProperty("$bit_key", "c4f3508aee6058f3");
		System.setProperty("$bit_secret", "966eedc1903454b8");

    	
        JdbcCommonCollects ins = JdbcCommonCollects.INSTANCE;
        String classname = "com.mysql.jdbc.NonRegisteringDriver";
        ClassLoader loader = SpringControllerCollectsTest.class.getClassLoader();
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass cclass = pool.get(classname);
        String className=classname;
        byte[] classfileBuffer=null;
        if (ins.isTarget(className, loader, cclass)) {
            ins.transform(loader, className, classfileBuffer, cclass);
            Class cla = cclass.toClass();
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("select * FROM city");
            boolean flag = ps.execute();
            Assert.isTrue(flag);
        } else {
            Assert.isTrue(false);
        }
        Thread.sleep(2000);
    }


    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true","root","admin");
     return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, "root", "admin");
        conn.getMetaData();
        int index = url.indexOf("?"); //$NON-NLS-1$
        if (index != -1) {
            String paramString = url.substring(index + 1, url.length());
            url = url.substring(0, index);
        }
        String dbName = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(dbName);
    }

}
