package com.example.springaop.jdbcTemplate.extends1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserDao2 extends JdbcDaoSupport {
    public void insert() {
        String sql = "INSERT INTO `tbl_user`(username,age) VALUES(?,?)";
        String username = UUID.randomUUID().toString().substring(0, 5);
        super.getJdbcTemplate().update(sql, username, 19);

    }
}
