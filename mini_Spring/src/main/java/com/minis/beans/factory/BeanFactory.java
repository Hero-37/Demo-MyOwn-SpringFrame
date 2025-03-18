package com.minis.beans.factory;

import com.minis.beans.BeansException;

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
     * 判断bean是否存在
     * @param beanName
     * @return
     */
    boolean containsBean(String beanName);

    /**
     * 注册bean
     * @param beanName
     * @param obj
     */
    void registerBean(String beanName, Object obj);

    /**
     * 判断bean是否是单例
     * @param beanName
     * @return
     */
    boolean isSingleton(String beanName);

    /**
     * 判断bean是否是原型
     * @param beanName
     * @return
     */
    boolean isPrototype(String beanName);

    /**
     * 获取bean的类型
     * @param beanName
     * @return
     */
    Class<?> getType(String beanName);
}
