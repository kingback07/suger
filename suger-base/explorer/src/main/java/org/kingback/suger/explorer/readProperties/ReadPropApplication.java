package org.kingback.suger.explorer.readProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReadPropApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReadPropApplication.class, args);
        ReadCustomYml readDemo = context.getBean(ReadCustomYml.class);

        String s = readDemo.getCustomYmlStr();
        System.out.println(s);
    }
}
