package com.IntroSpector.jdbc;

import lombok.Data;

/**
 * @author 陈孟飞
 * @date 2021/8/26
 */
@Data
public class DBEntity {
    private String driver;
    private String url;
    private String user;
    private String password;
}
