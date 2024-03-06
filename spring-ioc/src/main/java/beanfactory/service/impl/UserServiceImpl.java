package beanfactory.service.impl;


import beanfactory.service.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public void run() {
        System.out.println("user running");
    }
}
