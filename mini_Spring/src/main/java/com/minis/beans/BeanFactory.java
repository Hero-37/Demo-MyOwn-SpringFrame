package com.minis.beans;

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
    Boolean containsBean(String beanName);

    /**
     * 注册bean, 根据类型
     * @param beanName
     * @param obj
     */
    void registerBean(String beanName, Object obj);
}
