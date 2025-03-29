package com.minis.context;

/**
 * @author YuLong
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);

    /**
     * 添加监听器
     * @param listener
     */
    void addApplicationListener(ApplicationListener listener);
}
