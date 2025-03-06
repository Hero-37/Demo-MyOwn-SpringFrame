package com.spring;

/**
 * @author YuLong
 */
public interface InitializingBean {

    /**
     * 初始化
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
