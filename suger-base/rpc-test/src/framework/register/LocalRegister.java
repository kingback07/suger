package framework.register;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyang09
 * Created on 2021-02-17
 */
public class LocalRegister {

    private static ConcurrentHashMap<String, Class> localRoute = new ConcurrentHashMap<String,Class>();

    public static void register(String serviceName, Class clazz) {
        localRoute.put(serviceName, clazz);
    }

    public static Class find(String serviceName) {
        return localRoute.get(serviceName);
    }
}
