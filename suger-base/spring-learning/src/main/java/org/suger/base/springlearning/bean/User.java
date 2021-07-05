package org.suger.base.springlearning.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wangyang09
 * Created on 2021-04-28
 */
public class User implements ApplicationContextAware, BeanFactoryPostProcessor {
    private String userName;
    private BaseInfo baseInfo;

    private ApplicationContext applicationContext;

    public String getUserName() {
        return userName;
    }

    public void initBase(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void printUserInfo() {
        System.out.println("The User Name is : " + userName + ",And the base info is " + baseInfo.toString());
    }

    public void printBeanDefinition() {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext;
        BeanDefinition userDef = registry.getBeanDefinition("user");
        System.out.println(userDef);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext;
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setAttribute("name", "definitionDemo");
        definition.setBeanClass(DefinitionDemo.class);
        registry.registerBeanDefinition("defDemo", definition);
    }

}
