package com.own;

import com.own.service.UserService;
import com.spring.MyOwnApplicationContext;

/**
 * @author YuLong
 */
public class Test {
    public static void main(String[] args) {
        MyOwnApplicationContext applicationContext = new MyOwnApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
