package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mysql.jdbc.Driver;

/**
 * @author 陈孟飞
 * @date 2021/8/25
 */
public class DemoJDBC {
    public static void main(String[] args) throws Exception {
        // 加载驱动类
//        Class.forName("com.mysql.jdbc.Driver");

        // 通过DriverManager获取数据库连接
        String url = "jdbc:mysql://192.168.1.150/test";
        String user = "teamtalk";
        String password = "123456";
        Connection connection = (Connection) DriverManager.getConnection(
                url, user, password);

        PreparedStatement statement = (PreparedStatement) connection.prepareStatement(
                "insert persion (name, age) value (?, ?)");
        statement.setString(1, "hdu");
        statement.setInt(2, 21);
        statement.executeUpdate();

        ResultSet resultSet = statement.executeQuery("select * from persion");
        // 操作ResultSet结果集
        while (resultSet.next()) {
            // 第一种获取字段方式
            System.out.println(resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " + resultSet.getString(3));
        }

        // 关闭数据库连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
