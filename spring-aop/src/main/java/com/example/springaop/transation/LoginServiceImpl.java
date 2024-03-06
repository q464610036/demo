package com.example.springaop.transation;

public class LoginServiceImpl implements LoginService{
    /**
     * 登录方法，登录完保存log日志，修改用户状态
     */
    @Override
    public void login(){
            //执行业务层代码
            this.update();
            this.save();
    }

    @Override
    public void save(){
        System.out.println("写入日志");
    }

    @Override
    public void update(){
        System.out.println("修改为在线状态");
    }
}
