package com.minis.beans.factory.annotation;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * @author YuLong
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            // 对每一个属性进行判断, 如果有 @Autowired 注解, 则进行处理
            for (Field field : fields) {
                boolean isAutowired = field.isAnnotationPresent(Autowired.class);
                if (isAutowired) {
                    // 根据属性名找到同名的bean
                    String fieldName = field.getName();
                    Object autowiredObj = this.beanFactory.getBean(fieldName);
                    // 设置属性值完成注入
                    try {
                        field.setAccessible(true);
                        field.set(bean, autowiredObj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aotowire " + fieldName + " for bean " + beanName);
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory =  beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
