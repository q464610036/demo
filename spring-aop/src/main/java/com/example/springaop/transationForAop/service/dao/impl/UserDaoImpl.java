package com.example.springaop.transationForAop.service.dao.impl;

import com.example.springaop.transationForAop.service.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Integer userId) {
        String sql = "INSERT INTO `t_log`(user_id,log_type) VALUES(?,?)";
        jdbcTemplate.update(sql, userId, 1);

    }

    @Override
    public void update(Integer userId) {
        String sql = "update  `t_user` set online_status = ? where id = ? ";
        jdbcTemplate.update(sql, 1, userId);
    }
}
