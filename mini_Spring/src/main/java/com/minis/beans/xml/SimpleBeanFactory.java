package com.minis.beans.xml;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.*;
import com.minis.beans.factory.support.BeanDefinitionRegistry;
import com.minis.beans.factory.support.DefaultSingletonBeanRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YuLong
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();

    public SimpleBeanFactory() {
    }

    /**
     * 根据Bean名称获取对应的Bean实例, 容器的核心方法
     * @param beanName 需要获取的Bean名称
     * @return Bean实例对象
     * @throws BeansException 当Bean未注册或实例化失败时抛出异常
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        // 尝试从单例缓存中获取Bean实例
        Object singleton = this.getSingleton(beanName);
        // 如果此时还没有这个Bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition == null) {
                // 未找到注册的Bean名称时抛出异常
                throw new BeansException("No bean.");
            }
            // singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            singleton = createBean(beanDefinition);

            // 新注册这个 bean 实例
            this.registerSingleton(beanName, singleton);
        }
        return singleton;
    }

    /**
     * 注册BeanDefinition
     * @param beanDefinition
     */
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    @Override
    public boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return this.beanDefinitionMap.get(beanName).isSingleton();
    }

    @Override
    public boolean isPrototype(String beanName) {
        return this.beanDefinitionMap.get(beanName).isPrototype();
    }

    @Override
    public Class<?> getType(String name) {
        return (Class<?>) this.beanDefinitionMap.get(name).getBeanClass();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition bd) {
        this.beanDefinitionMap.put(name, bd);
        this.beanDefinitionNames.add(name);
        if (!bd.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    /**
     * 创建Bean实例
     * @param beanDefinition
     * @return
     */
    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;

        try {
            clz = Class.forName(beanDefinition.getId());
            // 处理构造器参数
            ArgumentValues argumentValues = beanDefinition.getConstructorArguments();
            // 如果有参数
            if (!argumentValues.isEmpty()) {
                // 参数类型
                Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
                // 参数值
                Object[] paramValues = new Object[argumentValues.getArgumentCount()];
                // 对每一个类型, 分数据类型分别处理
                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue indexedArgumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(indexedArgumentValue.getType()) ||
                    "lava.lang.String".equals(indexedArgumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = indexedArgumentValue.getValue();
                    } else if ("Integer".equals(indexedArgumentValue.getType()) ||
                    "java.lang.Integer".equals(indexedArgumentValue.getType())) {
                        paramValues[i] = Integer.class;
                        paramValues[i] = Integer.valueOf((String) indexedArgumentValue.getValue());
                    } else if ("int".equals(indexedArgumentValue.getType())) {
                        paramValues[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) indexedArgumentValue.getValue());
                    } else {  // 默认为 String
                        paramTypes[i] = String.class;
                        paramValues[i] = indexedArgumentValue.getValue();
                    }
                }
                // 按照特定构造器创建实例
                con = clz.getConstructor(paramTypes);
                obj = con.newInstance(paramValues);
            } else {
                // 直接创建实例
                obj = con.newInstance();
            }
        } catch (Exception e) {

        }

        // 处理属性
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.size(); i++) {
                // 对每一个属性, 分数据类型分别处理
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pType = propertyValue.getType();
                String pName = propertyValue.getName();
                Object pValue = propertyValue.getValue();
                Class<?>[] paramTypes = new Class<?>[1];
                if ("String".equals(pType) || "lava.lang.String".equals(pType)) {
                    paramTypes[0] = String.class;
                } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                    paramTypes[0] = Integer.class;
                    pValue = Integer.valueOf((String) pValue);
                } else if ("int".equals(pType)) {
                    paramTypes[0] = int.class;
                    pValue = Integer.valueOf((String) pValue);
                } else {
                    // 默认为 String
                    paramTypes[0] = String.class;
                }
                Object[] paramValues = new Object[1];
                paramValues[0] = pValue;

                // 按照 setXxxx 规范查找 setter 方法, 调用 setter 方法设置属性
                String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);
                Method method = null;
                try {
                    method = clz.getMethod(methodName, paramTypes);
                    method.invoke(obj, paramValues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
