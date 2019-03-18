package org.kingback.suger.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DemoApplication.class, args);
        String val="05000200";
        Long l=Long.valueOf(val);
        System.out.println(l);
    }

}
