package framework.proxy;

import framework.cluster.LoadBalance;
import framework.common.URL4Rpc;
import framework.protocol.Invocation;
import framework.protocol.http.Client4Http;
import framework.register.RemoteRegister;
import sun.nio.ch.ThreadPool;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 客户端代理类
 * 客户端通过此代理完成真正的远程调用
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class ProxyFactory {

    private ReentrantLock lock;

    //创建指定服务方法的实际代理类，此处简单使用java 自带的动态代理实现
    public static <T> T getInvoker(final Class interfaceType) {
        Client4Http client4Http = new Client4Http();
        return (T)Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[] {interfaceType}, (proxy, method, args) -> {
            // 解析出远程调用参数
            Invocation invocation = new Invocation(interfaceType.getName(), method.getName(), method.getParameterTypes(), args);
            // 从远程注册中心获取可用的服务列表（添加客户端本地缓存加速服务信息获取）
            String servieName = interfaceType.getName();
            List<URL4Rpc> availableUrls = RemoteRegister.get(servieName);
            //对于多个可用远程服务，使用LoadBalance 方法获取一个具体的URL
            URL4Rpc chooseUrl = LoadBalance.selectByRandom(availableUrls);
            return client4Http.send(chooseUrl, invocation);
        });
    }
}
