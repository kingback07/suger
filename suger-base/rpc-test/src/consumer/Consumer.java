package consumer;

import api.Communicate;
import framework.proxy.ProxyFactory;

/**
 * 消费者入口
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class Consumer {
    public static void main(String[] args) {
        Communicate communicate = ProxyFactory.getInvoker(Communicate.class);
        String res = communicate.sayHi("LiLei");
        System.out.println(res);

    }
}
