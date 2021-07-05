package org.suger.base.springlearning.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wangyang09
 * Created on 2021-05-10
 */
public class BaseInfo implements ApplicationContextAware, InitializingBean, BeanNameAware {
    private Integer age;
    private String nickName;

    private String beanName;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.age=10;
        this.nickName="joker";
        User user = applicationContext.getBean(User.class);
        user.initBase(this);
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "age=" + age +
                ", beanName='" + beanName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", applicationContext=" + applicationContext +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        this.beanName=name;
    }
}
