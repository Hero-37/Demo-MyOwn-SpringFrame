package com.minis.context;

import com.minis.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YuLong
 */
public class ClassPathXmlApplicationContext {

    /**
     * 对象信息集合
     */
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private Map<String, Object> singletons = new HashMap<>();

    /**
     * 构造器获取外部配置，解析出Bean的定义，形成内存映像
     * @param fileName
     */
    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    /**
     * 读取指定名称的XML文件并解析其中的Bean配置信息
     * @param fileName 要读取的XML文件的名称或路径
     * @return 无返回值
     */
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();

        try {
            /* 通过类加载器获取XML文件的URL路径 */
            Class<? extends ClassPathXmlApplicationContext> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();

            // 对配置文件中的每一个<bean>，进行处理
            for (Element element : (List<Element>) rootElement.elements()) {
                String beanId = element.attributeValue("id");
                String className = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanId, className);
                beanDefinitions.add(beanDefinition);
            }
        } catch (DocumentException e) {
            /* 捕获并处理XML文档解析异常 */
            e.printStackTrace();
        }
    }

    /**
     * 利用反射创建Bean实例，并存储在singletons中
     */
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 这是对外的一个方法，让外部程序从容器中获取Bean实例，会逐步演化成核心方法
     * @param beanName
     * @return
     */
   public Object getBean(String beanName) {
       return singletons.get(beanName);
   }
}
