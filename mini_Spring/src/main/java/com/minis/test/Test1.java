package com.minis.test;

import com.minis.beans.BeansException;
import com.minis.context.ClassPathXmlApplicationContext;

/**
 * @author YuLong
 */
public class Test1 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Aservice aservice = (Aservice) context.getBean("aService");
        aservice.sayHello();
    }
}
