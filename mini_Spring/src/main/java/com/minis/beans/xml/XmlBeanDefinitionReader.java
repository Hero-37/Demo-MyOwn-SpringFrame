package com.minis.beans.xml;

import com.minis.beans.factory.config.*;
import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.List;

/**
 * @author YuLong
 */
public class XmlBeanDefinitionReader {

    SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            // 处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues PVS = new PropertyValues();
            for (Element e : propertyElements) {
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                PVS.addPropertyValue(new PropertyValue(type, name, value));
            }
            beanDefinition.setPropertyValues(PVS);

            // 处理构造器属性
            List<Element> constructorElements = element.elements("constructor-arg");
            ArgumentValues AVS = new ArgumentValues();
            for (Element e : constructorElements) {
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                AVS.addArgumentValue(new ArgumentValue(type, name, value));
            }
            beanDefinition.setConstructorArguments(AVS);

            this.simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
