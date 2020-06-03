package org.kingback.suger.demo.demo4RPC.Second;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GrowtherB {

    @Resource(name = "secA")
    private IsayHi sayHi;

    public void welcome(String name) {
        sayHi.sayHi(name);
    }
}
