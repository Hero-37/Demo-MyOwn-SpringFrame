package com.spring;

/**
 * @author YuLong
 */
public interface BeanPostProcessor {

    /**
     * 初始化前操作
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 初始化后操作
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
