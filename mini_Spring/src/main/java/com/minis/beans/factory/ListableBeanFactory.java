package com.minis.beans.factory;

import com.minis.beans.BeansException;

import java.util.Map;

/**
 * @author YuLong
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 判断是否包含指定名称的 Bean 定义
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取 Bean 定义数量
     * @return
     */
    int getBeanDefinitionCount();

    /**
     * 获取所有 Bean 定义名称
     * @return
     */
    String[] getBeanDefinitionNames();

    /**
     * 根据类型获取 Bean 名称
     * @param type
     * @return
     */
    String[] getBeanNamesForType(Class<?> type);

    /**
     * 根据类型获取 Bean
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}
