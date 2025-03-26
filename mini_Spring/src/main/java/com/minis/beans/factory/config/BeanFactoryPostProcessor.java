package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * @author YuLong
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有Bean定义加载完成后，实例化Bean对象之前，提供修改Bean定义的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
