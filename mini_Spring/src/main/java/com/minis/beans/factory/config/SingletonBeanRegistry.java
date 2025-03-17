package com.minis.beans.factory.config;

/**
 * @author YuLong
 */
public interface SingletonBeanRegistry {
    /**
     * 注册单例对象
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 获取单例对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    /**
     * 判断单例对象是否存在
     * @param beanName
     * @return
     */
    boolean containsSingleton(String beanName);

    /**
     * 获取所有单例对象的名称
     * @return
     */
    String[] getSingletonNames();
}
