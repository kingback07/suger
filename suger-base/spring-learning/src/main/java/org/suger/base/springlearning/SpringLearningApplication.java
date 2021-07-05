package org.suger.base.springlearning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.suger.base.springlearning.bean.DefinitionDemo;
import org.suger.base.springlearning.bean.OrdinaryBeanB;
import org.suger.base.springlearning.bean.User;
import org.suger.base.springlearning.config.UserConfig;

public class SpringLearningApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(UserConfig.class);
        annotationConfigApplicationContext.register(OrdinaryBeanB.class);
        annotationConfigApplicationContext.refresh();

        User user = annotationConfigApplicationContext.getBean(User.class);
        user.printUserInfo();
        user.printBeanDefinition();

        DefinitionDemo definitionDemo= (DefinitionDemo) annotationConfigApplicationContext.getBean("defDemo");
        System.out.println(definitionDemo.toString());

        OrdinaryBeanB odBean = annotationConfigApplicationContext.getBean(OrdinaryBeanB.class);
        odBean.describe();

        annotationConfigApplicationContext.close();

    }

}
