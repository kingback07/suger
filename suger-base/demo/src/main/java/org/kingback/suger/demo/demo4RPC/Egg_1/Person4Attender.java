package org.kingback.suger.demo.demo4RPC.Egg_1;

/**
 * 参会人员
 */
public class Person4Attender implements IGrowther {
    private String name;
    private String myOpinion;

    AbstarctSalonOrganizer organizer;

    public Person4Attender(String name, AbstarctSalonOrganizer organizer) {
        //作为参会人员，初始化的时候需要首先报名
        this.name = name;
        myOpinion = "我还没想好说啥";
        organizer.register(this);
    }

    public void setMyOpinion(String myOpinion) {
        this.myOpinion = myOpinion;
    }

    @Override
    public void sayMyOpinion() {

        System.out.println("我的名字是:" + name + ",我有一个想法:" + myOpinion);

    }


}
