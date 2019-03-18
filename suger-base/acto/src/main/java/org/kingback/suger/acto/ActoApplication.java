package org.kingback.suger.acto;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActoApplication {

    public static void main(String[] args) {

        SpringApplication.run(ActoApplication.class, args);
    }


    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        // put logback-access.xml in src/main/resources/conf
        tomcat.addContextValves(new LogbackValve());
        return tomcat;
    }
}
