package com.example.springtransaction.annotation.service.impl;

import com.example.springtransaction.annotation.service.LoginService;
import com.example.springtransaction.annotation.service.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userDao;
    /**
     * 登录方法，登录完保存log日志，修改用户状态
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void login(Integer userId){
            //执行业务层代码
            this.update(userId);
            this.save(userId);
            int i = 1/0;
    }

    @Override
    @Transactional
    public void save(Integer userId){
        System.out.println("记录登录log");
        userDao.save(userId);
    }

    @Override
    @Transactional
    public void update(Integer userId){
        System.out.println("修改为在线状态");
        userDao.update(userId);
    }
}
