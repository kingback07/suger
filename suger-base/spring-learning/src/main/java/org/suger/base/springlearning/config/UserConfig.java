package org.suger.base.springlearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.suger.base.springlearning.bean.BaseInfo;
import org.suger.base.springlearning.bean.User;

/**
 * @author wangyang09
 * Created on 2021-04-28
 */
@Configuration
public class UserConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setUserName("Default");
        return user;
    }

    @Bean
    public BaseInfo theBase(){
        BaseInfo baseInfo=new BaseInfo();
        return baseInfo;
    }
}
