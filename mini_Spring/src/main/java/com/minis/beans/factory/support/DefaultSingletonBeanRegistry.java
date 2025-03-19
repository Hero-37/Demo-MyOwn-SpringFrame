package com.minis.beans.factory.support;

import com.minis.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YuLong
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 容器中存放所有 bean 的名称列表
     */
    protected List<String> beanNames = new ArrayList<>();

    /**
     * 容器中存放所有 bean 实例的 map
     */
    protected Map<String, Object> singletons = new ConcurrentHashMap<>();

    /**
     * 容器中存放所有 early bean 实例的 map, bean 的早期毛胚实例
     */
    protected Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletons) {
            this.singletons.put(beanName, singletonObject);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.singletons.remove(beanName);
            this.beanNames.remove(beanName);
        }
    }
}
