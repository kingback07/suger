package org.kingback.suger.explorer.tryspring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.kingback.suger.explorer.tryspring")
public class doApplication {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(doApplication.class);
        IHappyRun happyRun= (IHappyRun) context.getBean("suger");
        happyRun.happyrun();
        System.out.println("the happy run bean:"+happyRun.toString());
    }

}
