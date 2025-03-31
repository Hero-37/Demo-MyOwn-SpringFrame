package com.minis.context;

import com.minis.beans.BeansException;
import com.minis.beans.factory.ListableBeanFactory;
import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.core.env.Environment;
import com.minis.core.env.EnvironmentCapable;

/**
 * @author YuLong
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ConfigurableListableBeanFactory,
        ApplicationEventPublisher{
    /**
     * 获取应用名称
     * @return
     */
    String getApplicationName();

    /**
     * 获取应用启动时间
     * @return
     */
    long getStartupDate();

    /**
     * 获取应用上下文
     * @return
     * @throws BeansException
     */
    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    /**
     * 设置环境
     * @param environment
     */
    void setEnvironment(Environment environment);

    /**
     * 获取环境
     * @return
     */
    @Override
    Environment getEnvironment();

    /**
     * 添加BeanFactory后置处理器
     * @param postProcessor
     */
    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    /**
     * 刷新应用上下文
     * @throws BeansException
     * @throws IllegalStateException
     */
    void refresh() throws BeansException, IllegalStateException;

    /**
     * 关闭应用上下文
     */
    void close();

    /**
     * 判断应用上下文是否激活
     * @return
     */
    boolean isActive();
}
