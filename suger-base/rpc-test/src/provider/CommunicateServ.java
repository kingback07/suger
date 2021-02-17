package provider;

import api.Communicate;

/**
 * @author wangyang09
 * Created on 2021-02-17
 */
public class CommunicateServ implements Communicate {
    public String sayHi(String name) {
        return String.format("%s,Nice to meet you", name);
    }
}
