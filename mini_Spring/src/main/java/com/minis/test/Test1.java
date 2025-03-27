package com.minis.test;

import com.minis.beans.BeansException;
import com.minis.context.ClassPathXmlApplicationContext;

/**
 * @author YuLong
 */
public class Test1 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // AserviceImpl aserviceImpl = (AserviceImpl) context.getBean("aService");
        // aserviceImpl.setProperty1("Hello World!");
        // aserviceImpl.sayHello();
        BaseService baseService = (BaseService) context.getBean("baseservice");
        baseService.sayHello();
    }
}
