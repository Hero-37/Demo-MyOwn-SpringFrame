package com.own;

import com.own.service.UserService;
import com.own.service.UserServiceImpl;
import com.spring.MyOwnApplicationContext;

/**
 * @author YuLong
 */
public class Test {
    public static void main(String[] args) {
        MyOwnApplicationContext applicationContext = new MyOwnApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        // 1. 代理对象 2. 业务方法
        userService.test1();
    }
}
