package org.kingback.suger.explorer.tryspring;

import org.springframework.stereotype.Component;

@Component
public class Suger implements IHappyRun {

    @Override
    public void happyrun() {
        System.out.println("I'm Suger,I'm run very happy");
    }
}
