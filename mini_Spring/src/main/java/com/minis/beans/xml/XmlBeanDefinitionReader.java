package com.minis.beans.xml;

import com.minis.BeanDefinition;
import com.minis.beans.BeanFactory;
import com.minis.core.Resource;
import org.dom4j.Element;

/**
 * @author YuLong
 */
public class XmlBeanDefinitionReader {

    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            this.beanFactory.registerBeanDefinition( beanDefinition);
        }
    }
}
