package org.kingback.suger.explorer.tryspring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class AnimalFactory implements BeanPostProcessor, BeanNameAware, SmartInitializingSingleton, BeanFactoryPostProcessor {

    IHappyRun happyRun;

    ClassLoader classLoader;

    public void createAnimal(String animalName) {


    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("suger".equals(beanName)) {
            System.out.println("======= before post process:" + bean.toString());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("suger".equals(beanName)) {
            System.out.println("======= after post process:" + bean.toString());
            Object regionBean = bean;
            bean = Proxy.newProxyInstance(classLoader,
                    new Class[]{IHappyRun.class},
                    (proxy, method, args) -> {
                        System.out.println("=== this is the proxy log prefix=====");
                        Object res = method.invoke(regionBean, args);
                        System.out.println("=== this is the proxy log suffix=====");
                        return res;
            });
        }
        return bean;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("============set bean name method :" + s);
    }


    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("============afterSingletonsInstantiated method ");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        classLoader = configurableListableBeanFactory.getBeanClassLoader();
        BeanDefinition bd = configurableListableBeanFactory.getBeanDefinition("suger");
        bd.setBeanClassName("org.kingback.suger.explorer.tryspring.Coffee");
        System.out.println("============postProcessBeanFactory method :" + bd.getBeanClassName());
    }
}
