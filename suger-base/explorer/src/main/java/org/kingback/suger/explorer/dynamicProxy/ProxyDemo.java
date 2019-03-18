package org.kingback.suger.explorer.dynamicProxy;

/**
 * 被代理实体类
 * 实现接口方法
 */
public class ProxyDemo implements IProxyFunction {

    @Override
    public void proxyFunc(Integer i){
        if(i>=10){
            System.out.println("I > 10");
        }else{
            System.out.println("I < 10");
        }
    }

}
