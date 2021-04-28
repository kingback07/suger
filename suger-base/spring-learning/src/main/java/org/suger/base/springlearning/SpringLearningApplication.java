package org.suger.base.springlearning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.suger.base.springlearning.bean.User;
import org.suger.base.springlearning.config.UserConfig;

public class SpringLearningApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(UserConfig.class);

        annotationConfigApplicationContext.refresh();

        User user = annotationConfigApplicationContext.getBean(User.class);
        user.printUserInfo();


        annotationConfigApplicationContext.close();
    }

}
