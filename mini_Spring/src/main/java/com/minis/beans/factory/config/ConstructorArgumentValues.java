package com.minis.beans.factory.config;

import java.util.*;

/**
 * @author YuLong
 */
public class ConstructorArgumentValues {
    private final Map<Integer, ConstructorArgumentValue> indexedArgumentValues = new HashMap<>();
    private final List<ConstructorArgumentValue> genericConstructorArgumentValues = new LinkedList<>();

    public ConstructorArgumentValues() {
    }

    /**
     * 将指定键和对应的参数值对象存储到索引参数值映射中。
     *
     * @param key            用于标识参数值的键
     * @param newValue       需要存储的参数值对象
     */
    public void addArgumentValue(Integer key, ConstructorArgumentValue newValue) {
        this.indexedArgumentValues.put(key, newValue);
    }

    /**
     * 判断索引参数值映射中是否包含指定索引的参数值对象。
     * @param index
     * @return
     */
    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    /**
     * 获取索引参数值映射中指定索引的参数值对象。
     * @param index
     * @return
     */
    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        return this.genericConstructorArgumentValues.get(index);
    }

    /**
     * 将指定值和对应的参数类型添加到通用参数值列表中。
     * @param value
     * @param type
     */
    private void addGenericArgumentValue(Object value, String type) {
        this.genericConstructorArgumentValues.add(new ConstructorArgumentValue(value, type));
    }

    /**
     * 将指定参数值对象添加到通用参数值列表中。
     * @param newValue
     */
    public void addArgumentValue(ConstructorArgumentValue newValue) {
        if (newValue.getName() != null) {
            for (Iterator<ConstructorArgumentValue> it = this.genericConstructorArgumentValues.iterator(); it.hasNext();) {
                ConstructorArgumentValue currentValue = it.next();
                if (newValue.getName().equals(currentValue.getName())) {
                    it.remove();
                }
            }
        }
        this.genericConstructorArgumentValues.add(newValue);
    }

    public ConstructorArgumentValue getGenericArgumentValue(String requiredName) {
        for (ConstructorArgumentValue valueHolder : this.genericConstructorArgumentValues) {
            boolean isNotExist = valueHolder.getName() != null && (requiredName == null || !valueHolder.getName().equals(requiredName));
            if (isNotExist) {
                continue;
            }
            return valueHolder;
        }
        return null;
    }

    /**
     * 获取通用参数值列表的大小。
     */
    public int getArgumentCount() {
        return this.genericConstructorArgumentValues.size();
    }

    /**
     * 判断通用参数值列表是否为空。
     * @return
     */
    public boolean isEmpty() {
        return this.genericConstructorArgumentValues.isEmpty();
    }
}
