package org.suger.base.springlearning.bean;

/**
 * @author wangyang09 wangyang09@kuaishou.com>
 * Created on 2021-04-28
 */
public class User {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void printUserInfo(){
        System.out.println("The User Name is : "+userName);
    }
}
