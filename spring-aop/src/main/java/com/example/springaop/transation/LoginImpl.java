package com.example.springaop.transation;

public class LoginImpl {
    /**
     * 登录方法，登录完保存log日志，修改用户状态
     */
    public void login(){
        try {
            //开启事务
            TransactionManager.begin();
            //执行业务层代码
            this.update();
            this.save();
            //提交事务
            TransactionManager.commit();
        } catch (Exception e) {
            //事务回滚
            TransactionManager.rollback();
        } finally {
            //释放链接
            TransactionManager.release();
        }
    }

    public void save(){
        //写入登录日志
    }

    public void update(){
        //修改登录状态
    }
}
