package org.kingback.suger.demo.demo4RPC.Egg_1;

import java.util.ArrayList;

/**
 * 一个普通角色
 * 通过适配器原则，则可以赋能成沙龙组织者和Growther
 */
public class Person4Organizer extends AbstarctSalonOrganizer implements IGrowther {
    @Override
    public void register(IGrowther growther) {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(growther);
        //Todo:对于报名者的个性化处理
    }

    @Override
    public void notifyEveryOne() {
        //Todo:个性化处理，最后通知大家畅所欲言
        if (friends != null && friends.size() >= 0) {
            friends.forEach(t -> t.sayMyOpinion());
        }
    }

    @Override
    public void sayMyOpinion() {
        System.out.println("大家就荆州的问题说说意见吧");
    }
}
