package com.minis.core.env;

/**
 * @author YuLong
 */
public interface Environment extends PropertyResolver {
    /**
     * 获取当前活动的环境
     * @return
     */
    String[] getActiveProfiles();

    /**
     * 获取默认环境
     * @return
     */
    String[] getDefaultProfiles();

    /**
     * 判断是否接受指定的环境
     * @param profiles
     * @return
     */
    boolean acceptsProfiles(String... profiles);
}
