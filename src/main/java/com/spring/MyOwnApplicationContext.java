package com.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YuLong
 */
public class MyOwnApplicationContext {

    // 配置类
    private Class configClass;

    /**
     * 单例池
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * Bean 定义信息
     */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public MyOwnApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 解析配置类
        // ComponentScan 注解--> 扫描路径 --> 扫描 --> 为每个类生成 BeanDefinition 对象 --> 存入 BeanDefinitionMap
        scan(configClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            // 单例 bean
            if ("singleton".equals(beanDefinition.getScope())) {
                // 创建当前 bean 对象
                Object bean = createBean(beanDefinition);
                singletonObjects.put(beanName, bean);
            } else {

            }
        }
    }

    /**
     * 创建一个 bean
     * @param beanDefinition
     * @return
     */
    public Object createBean(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // 依赖注入, 对属性进行赋值
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    // 属性有 Autowired 注解, 才进行赋值
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 扫描配置类的路径
     * @param configClass
     */
    private void scan(Class configClass) {
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        // 扫描路径
        String scanPath = componentScanAnnotation.value();
        System.out.println(scanPath);

        // BootStrap--->jre/lib
        // Ext--->jre/ext/lib
        // app--->classpath---> 应用类加载器
        // 获取 应用类加载器
        ClassLoader classLoader = MyOwnApplicationContext.class.getClassLoader();
        scanPath = scanPath.replace(".", "/");
        URL resource = classLoader.getResource(scanPath);
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println(f);
                String fileName = f.getAbsolutePath();
                if (fileName.endsWith(".class")) {
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                    className = className.replace("\\", ".");
                    System.out.println(className);
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            // 表示当前这个类是一个 Bean
                            // 解析类 并生成 BeanDefinition(Bean定义信息), 判断当前类是 单例bean 还是 prototype bean
                            Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);

                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 根据类型获取 bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = singletonObjects.get(beanName);
                return bean;
            } else {
                // 创建 bean 对象
                Object bean = createBean(beanDefinition);
                return bean;
            }
        } else {
            // 不存在对应的 bean
            throw new NullPointerException();
        }
    }
}
