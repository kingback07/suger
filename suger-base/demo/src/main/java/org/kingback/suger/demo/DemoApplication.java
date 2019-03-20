package org.kingback.suger.demo;

import org.kingback.suger.demo.demo4RPC.Egg_1.Person4Attender;
import org.kingback.suger.demo.demo4RPC.Egg_1.Person4Organizer;
import org.kingback.suger.demo.demo4RPC.First.GrowtherA;
import org.kingback.suger.demo.demo4RPC.Second.GrowtherB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        final String NEW_PERSON="polo";
        //1st try,传统的对象使用
        GrowtherA growther=new GrowtherA();
        growther.welcome(NEW_PERSON);



        //2nd try,开放-封闭原则
        //获取spring容器，从容器中获取Growther实例
        ConfigurableApplicationContext context= SpringApplication.run(DemoApplication.class, args);
        GrowtherB growtherB=context.getBean(GrowtherB.class);
        growtherB.welcome(NEW_PERSON);

//        Thread t1=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ConfigurableApplicationContext context= SpringApplication.run(DemoApplication.class, args);
//                GrowtherB growtherB=context.getBean(GrowtherB.class);
//                growtherB.welcome(NEW_PERSON);
//            }
//        });
//        t1.start();
//        t1.run();


        //3rd 彩蛋：观察者模式体现 ---组织一场沙龙，发起所有人讨论
        //1.确定一个组织人
        Person4Organizer organizer=new Person4Organizer();
        organizer.register(organizer);
        //2.初始化N名参会者
        Person4Attender attenderA=new Person4Attender("关羽",organizer);
        Person4Attender attenderB=new Person4Attender("张飞",organizer);
        Person4Attender attenderC=new Person4Attender("赵云",organizer);
        Person4Attender attenderD=new Person4Attender("马超",organizer);
        Person4Attender attenderE=new Person4Attender("黄忠",organizer);

        attenderA.setMyOpinion("孙权不足为惧！");
        attenderB.setMyOpinion("二哥说的对呀！");
        attenderC.setMyOpinion("二位将军还是谨慎一些");

        organizer.notifyEveryOne();


        //---------华丽的分割线：以上是单机调用时的方法抽象描述--------------//


    }

}
