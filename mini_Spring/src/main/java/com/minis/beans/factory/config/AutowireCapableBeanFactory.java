package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * @author YuLong
 */
public class AutowireCapableBeanFactory implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean containsBean(String beanName) {
        return false;
    }

    @Override
    public void registerBean(String beanName, Object obj) {

    }

    @Override
    public boolean isSingleton(String beanName) {
        return false;
    }

    @Override
    public boolean isPrototype(String beanName) {
        return false;
    }

    @Override
    public Class<?> getType(String beanName) {
        return null;
    }
}
