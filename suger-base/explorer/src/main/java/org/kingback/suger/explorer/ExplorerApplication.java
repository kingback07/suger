package org.kingback.suger.explorer;

import org.kingback.suger.explorer.dynamicProxy.ProxyImpl;
import org.kingback.suger.explorer.interview.Testby2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExplorerApplication {

    public static void main(String[] args) {
        boolean resA=Testby2.isTrue(8);
        boolean resB=Testby2.isTrue(1024);
        try {
            int numA=Testby2.quaNum4two(8);
            int numB=Testby2.quaNum4two(1024);
            System.out.println("numA:"+numA+"&numB:"+numB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("resA:"+resA+"&ResB:"+resB);

        //动态代理测试
        ProxyImpl.doProxyFunction(4);
    }
}
