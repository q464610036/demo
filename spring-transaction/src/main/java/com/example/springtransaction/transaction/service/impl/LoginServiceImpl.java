package com.example.springtransaction.transaction.service.impl;

import com.example.springtransaction.transaction.service.LoginService;
import com.example.springtransaction.transaction.service.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userDao;
    /**
     * 登录方法，登录完保存log日志，修改用户状态
     */
    @Override
    public void login(Integer userId){
            //执行业务层代码
            this.update(userId);
            this.save(userId);
    }

    @Override
    public void save(Integer userId){
        System.out.println("记录登录log");
        userDao.save(userId);
    }

    @Override
    public void update(Integer userId){
        System.out.println("修改为在线状态");
        userDao.update(userId);
    }
}
