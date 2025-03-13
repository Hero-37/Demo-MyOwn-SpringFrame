package com.minis.beans.xml;

import com.minis.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YuLong
 */
public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {
    }

    /**
     * 根据Bean名称获取对应的Bean实例
     * @param beanName 需要获取的Bean名称
     * @return Bean实例对象
     * @throws BeansException 当Bean未注册或实例化失败时抛出异常
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        // 尝试从单例缓存中获取Bean实例
        Object singleton = singletons.get(beanName);
        // 如果此时还没有这个Bean的实例，则获取它的定义来创建实例
        if (singleton == null) {
            // 查找Bean名称在注册表中的索引位置
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                // 未找到注册的Bean名称时抛出异常
                throw new BeansException();
            } else {
                // 获取Bean的定义
                BeanDefinition beanDefinition = beanDefinitions.get(i);
                try {
                    // 通过反射创建Bean实例并存入单例缓存
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                    singletons.put(beanDefinition.getId(), singleton);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return singleton;
    }


    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
