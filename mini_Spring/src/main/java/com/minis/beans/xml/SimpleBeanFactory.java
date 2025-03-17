package com.minis.beans.xml;

import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;
import com.minis.beans.factory.support.DefaultSingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YuLong
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    public SimpleBeanFactory() {
    }

    /**
     * 根据Bean名称获取对应的Bean实例, 容器的核心方法
     * @param beanName 需要获取的Bean名称
     * @return Bean实例对象
     * @throws BeansException 当Bean未注册或实例化失败时抛出异常
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        // 尝试从单例缓存中获取Bean实例
        Object singleton = this.getSingleton(beanName);
        // 如果此时还没有这个Bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                // 未找到注册的Bean名称时抛出异常
                throw new BeansException("No bean.");
            }
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // 新注册这个 bean 实例
            this.registerBean(beanName, singleton);
        }
        return singleton;
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public Boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }
}
