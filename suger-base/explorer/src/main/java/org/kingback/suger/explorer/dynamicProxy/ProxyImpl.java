package org.kingback.suger.explorer.dynamicProxy;

import java.lang.reflect.Proxy;

public class ProxyImpl {

    /**
     * 动态代理操作实例
     *
     * @param x
     */
    public static void doProxyFunction(int x) {
        IProxyFunction proxyFunction = new ProxyDemo();//申明一个实体类

        DemoInvoke invoke = new DemoInvoke(proxyFunction);//为Invoke设置对应的实体类

        //使用动态代理生成动态代理对象，此对象以接口类型返回
        /**
         * 此处第二个参数为什么不用接口类型而是需要实体类类型？
         * getInterfaces()方法标识获取此对象所有实现的接口类型
         */
        IProxyFunction proxyIns = (IProxyFunction) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IProxyFunction.class}, invoke);
        //调用接口方法，实际上是使用动态生成的代理类执行此方法
        proxyIns.proxyFunc(x);
    }

    public static void main(String[] args){
        ProxyImpl.doProxyFunction(3);
    }
}
