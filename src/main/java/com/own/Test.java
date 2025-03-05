package com.own;

import com.spring.MyOwnApplicationContext;

/**
 * @author YuLong
 */
public class Test {
    public static void main(String[] args) {
        MyOwnApplicationContext applicationContext = new MyOwnApplicationContext(AppConfig.class);
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
    }
}
