package com.minis.beans.factory.annotation;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Yulong Duan
 * @date 2025年04月08日 1:32
 */
public class RequireAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            // 对每一个属性进行判断, 如果有 @Require 注解, 则进行处理
            for (Field field : fields) {
                boolean isRequire = field.isAnnotationPresent(Require.class);
                if (isRequire) {
                    // 根据属性名找到组装 getXXX 方法
                    String fieldName = field.getName();
                    // 按照 getXXX 规范查找 getter 方法, 调用 getter 方法获取属性值
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class<?> clz = bean.getClass();
                    Object paramValue = null;
                    try {
                        Method method = clz.getMethod(methodName);
                        paramValue = method.invoke(bean);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("require " + fieldName + " for bean " + beanName);
                    if (paramValue == null) {
                        throw new BeansException("The current attribute " + fieldName + " cannot be empty");
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {

    }
}
