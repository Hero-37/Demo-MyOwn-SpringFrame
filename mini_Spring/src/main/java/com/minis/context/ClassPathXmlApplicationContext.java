package com.minis.context;

import com.minis.beans.factory.BeanFactory;
import com.minis.beans.BeansException;
import com.minis.beans.xml.SimpleBeanFactory;
import com.minis.beans.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * @author YuLong
 */
public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {

    BeanFactory beanFactory;

    /**
     * context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
     */
    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((SimpleBeanFactory) beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    /**
     * context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
     * @param beanName 需要获取的Bean的名称
     * @return 指定名称的Bean实例，类型为Object
     * @throws BeansException 当Bean未找到或发生其他bean检索异常时抛出
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.beanFactory.containsBean(beanName);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.beanFactory.registerBean(beanName, obj);
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

    @Override
    public void publishEvent(ApplicationEvent event) {

    }
}
