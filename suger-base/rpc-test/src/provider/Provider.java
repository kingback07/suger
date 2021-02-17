package provider;

import api.Communicate;
import framework.common.URL4Rpc;
import framework.protocol.http.HttpServer4Tomcat;
import framework.register.LocalRegister;
import framework.register.RemoteRegister;

/**
 * @author wangyang09
 * Created on 2021-02-17
 */
public class Provider {

    public static void main(String[] args) {

        final String SERVICE_NAME = Communicate.class.getName();
        URL4Rpc url4Rpc = new URL4Rpc("localhost", 1314);

        // 本地服务注册
        LocalRegister.register(SERVICE_NAME, CommunicateServ.class);

        // 远程服务注册
        RemoteRegister.regist(SERVICE_NAME, url4Rpc);

        // 本地服务启动
        HttpServer4Tomcat server = new HttpServer4Tomcat();
        server.start(url4Rpc);
    }
}
