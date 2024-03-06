package beanfactory;


import beanfactory.service.IUserService;

public class BeanFactoryMain {
    public static void main(String[] args) {
        IUserService userService = (IUserService)BeanFactory.getBean("userService");
        userService.run();
    }
}
