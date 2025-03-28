package com.minis.beans.factory.config;

import com.minis.beans.factory.BeanFactory;

/**
 * @author YuLong
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 注册一个BeanPostProcessor
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 获取BeanPostProcessor的数量
     * @return
     */
    int getBeanPostProcessorCount();

    /**
     * 注册一个bean的依赖
     * @param beanName
     * @param dependentBeanName
     */
    void registerDependentBeans(String beanName, String dependentBeanName);

    /**
     * 获取bean的依赖
     * @param beanName
     * @return
     */
    String[] getDependentBeans(String beanName);

    /**
     * 获取bean的依赖
     * @param beanName
     * @return
     */
    String[] getDependenciesForBean(String beanName);
}
