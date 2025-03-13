package com.minis.beans;

import com.minis.BeanDefinition;

/**
 * @author YuLong
 */
public interface BeanFactory {
    /**
     * 根据beanName获取bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 注册bean
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);
}
