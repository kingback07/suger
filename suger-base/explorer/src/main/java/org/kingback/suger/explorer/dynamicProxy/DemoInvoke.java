package org.kingback.suger.explorer.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类：
 * 继承InvocationHandler接口，在invoke中添加增加操作逻辑
 * 设置关联的实体类
 */
public class DemoInvoke implements InvocationHandler {

    private Object target;

    public DemoInvoke(Object target){
        this.target=target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Start the Proxy Function:"+args[0].toString());
        Object res=method.invoke(target,args);
        System.out.println("End the Proxy Function:"+args[0].toString());
        return res;
    }
}
