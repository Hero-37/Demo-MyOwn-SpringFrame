package com.minis.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YuLong
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList;

    public PropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

    public int size() {
        return this.propertyValueList.size();
    }

    /**
     * 添加属性值
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    /**
     * 添加属性值
     * @param propertyName
     * @param propertyValue
     */
    public void addPropertyValue(String propertyName, Object propertyValue) {
        addPropertyValue(new PropertyValue(propertyName, propertyValue));
    }

    /**
     * 删除属性值
     * @param pv
     */
    public void removePropertyValue(PropertyValue pv) {
        this.propertyValueList.remove(pv);
    }

    /**
     * 删除属性值
     * @param propertyName
     */
    public void removePropertyValue(String propertyName) {
        this.propertyValueList.remove(getPropertyValue(propertyName));
    }

    /**
     * 获取属性值
     * @return
     */
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[this.propertyValueList.size()]);
    }

    /**
     * 获取属性值
     */
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    /**
     * 获取属性值
     * @param propertyName
     * @return
     */
    public Object get(String propertyName) {
        PropertyValue pv = getPropertyValue(propertyName);
        return pv != null ? pv.getValue() : null;
    }

    /**
     * 判断属性值是否存在
     * @param propertyName
     * @return
     */
    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    /**
     * 判断属性值是否为空
     * @return
     */
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }
}
