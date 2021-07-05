package org.suger.base.springlearning.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangyang09
 * Created on 2021-05-17
 */
@ComponentScan("org.suger.base.springlearning.bean")
public class OrdinaryBeanB {

    @Autowired
    private OrdinaryBeanA odBean;

    private String description;

    public void describe() {
        System.out.println("This is OrdinaryBeanB,And the Autowired Bean is:" + odBean.toString());
    }
}
