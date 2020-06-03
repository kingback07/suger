package org.kingback.suger.demo.demo4RPC.First;

public class GrowtherA {

    private FirstTry firstTry;

    public GrowtherA(FirstTry firstTry) {
        this.firstTry = firstTry;
    }

    public GrowtherA() {
        this.firstTry = new FirstTry();
    }

    public void welcome(String name) {
        firstTry.sayHi(name);
    }

}
