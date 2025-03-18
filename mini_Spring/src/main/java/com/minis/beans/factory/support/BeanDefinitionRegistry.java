package com.minis.beans.factory.support;

import com.minis.beans.factory.config.BeanDefinition;

/**
 * @author YuLong
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册 BeanDefinition
     * @param name
     * @param bd
     */
    void registerBeanDefinition(String name, BeanDefinition bd);

    /**
     * 移除 BeanDefinition
     * @param name
     */
    void removeBeanDefinition(String name);

    /**
     * 获取 BeanDefinition
     * @param name
     * @return
     */
    BeanDefinition getBeanDefinition(String name);

    /**
     * 判断是否存在 BeanDefinition
     * @param name
     * @return
     */
    boolean containsBeanDefinition(String name);
}
