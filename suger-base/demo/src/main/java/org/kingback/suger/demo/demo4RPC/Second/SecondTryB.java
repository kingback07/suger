package org.kingback.suger.demo.demo4RPC.Second;

import org.springframework.stereotype.Component;

@Component(value = "secB")
public class SecondTryB implements IsayHi {
    @Override
    public void sayHi(String name) {
        System.out.println("Hi " + name + ",Welcome to Growth,Let's have some fun!");
    }
}
