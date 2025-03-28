package com.minis.core.env;

/**
 * @author YuLong
 */
public interface PropertyResolver {
    /**
     * 判断是否存在该属性
     * @param key
     * @return
     */
    boolean containsProperty(String key);

    /**
     * 获取属性
     * @param key
     * @return
     */
    String getProperty(String key);

    /**
     * 获取属性，如果找不到，则返回默认值
     * @param key
     * @param defaultValue
     * @return
     */
    String getProperty(String key, String defaultValue);

    /**
     * 获取属性
     * @param key
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T getProperty(String key, Class<T> targetType);

    /**
     * 获取属性，如果找不到，则返回默认值
     * @param key
     * @param targetType
     * @param defaultValue
     * @param <T>
     * @return
     */
    <T> T getProperty(String key, Class<T> targetType, T defaultValue);

    /**
     * 获取属性
     * @param key
     * @param targetType
     * @param <T>
     * @return
     */
    <T> Class<T> getPropertyAsClass(String key, Class<T> targetType);

    /**
     * 获取属性，如果找不到，则抛出异常
     * @param key
     * @return
     * @throws IllegalStateException
     */
    String getRequiredProperty(String key) throws IllegalStateException;

    /**
     * 获取属性，如果找不到，则抛出异常
     * @param key
     * @param targetType
     * @param <T>
     * @return
     * @throws IllegalStateException
     */
    <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException;

    /**
     * 解析占位符
     * @param text
     * @return
     */
    String resolvePlaceholders(String text);

    /**
     * 解析占位符，如果找不到，则抛出异常
     * @param text
     * @return
     * @throws IllegalArgumentException
     */
    String resolveRequiredPlaceholders(String text) throws IllegalArgumentException;
}
