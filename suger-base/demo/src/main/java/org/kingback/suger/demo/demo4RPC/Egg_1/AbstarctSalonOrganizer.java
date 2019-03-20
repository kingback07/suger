package org.kingback.suger.demo.demo4RPC.Egg_1;

import java.util.List;

//沙龙组织者角色，抽象概念
public abstract class AbstarctSalonOrganizer {

    List<IGrowther> friends = null;

    //开放报名入口，注册沙龙报名者
    public abstract void register(IGrowther growther);

    //通知大家畅所欲言
    public abstract void notifyEveryOne();

    //组织沙龙
    public void organizeTheSalon(){
        //Todo:排期，一系列准备工作，准备发言……

        notifyEveryOne();

    }

}
