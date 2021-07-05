package org.suger.base.springlearning.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author wangyang09
 * Created on 2021-05-11
 */
public class DefinitionDemo implements BeanDefinitionRegistryPostProcessor, BeanFactoryPostProcessor {

    private String name;

    @Override
    public String toString() {
        return "DefinitionDemo{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
