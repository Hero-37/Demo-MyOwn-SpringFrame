package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * @author YuLong
 */
public interface BeanPostProcessor {
    /**
     * 在初始化之前对 bean 进行后置处理
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在初始化之后对 bean 进行后置处理
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);

    /**
     * 设置 beanFactory
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
