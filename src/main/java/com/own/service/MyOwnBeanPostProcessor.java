package com.own.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

/**
 * @author YuLong
 */
@Component
public class MyOwnBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)) {
            System.out.println("初始化前----");
            ((UserService) bean).setBeanName("初始化BeanName");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后-----");
        return bean;
    }
}
