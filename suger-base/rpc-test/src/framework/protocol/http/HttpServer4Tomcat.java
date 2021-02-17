package framework.protocol.http;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

import framework.common.URL4Rpc;
import framework.protocol.IServer;

/**
 * 对支持HTTP protocol 服务的一种实现，当然可以使用 jetty，netty 等方式完成类似实现
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class HttpServer4Tomcat implements IServer {

    public void start(URL4Rpc url4Rpc) {

        /**
         * tomcat 内部结构
         * Server 作为服务容器，包含多个service，具体关系查询 server and service
         * Service指向一个具体的服务实现，包含Connector & Container
         * Connector 设置port
         * Container： Engine - Host - Context - Wrapper
         * @Author: wangyang09
         * @Date 2021/2/17
         **/

        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer(); //初始化构造 server & service
        Service service = server.findService("Tomcat");//查看getServer方法源码，默认servicename 为 Tomcat

        //connector 配置端口
        Connector connector = new Connector();
        connector.setPort(url4Rpc.getPort());

        //engine 配置默认hostname
        Engine engine = new StandardEngine();
        engine.setDefaultHost(url4Rpc.getHostname());

        //host 配置 hostname
        Host host = new StandardHost();
        host.setName(url4Rpc.getHostname());

        //context 配置path
        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        //开始套娃，完成engine配置
        host.addChild(context);
        engine.addChild(host);

        //engine+connector -》 service
        service.setContainer(engine);
        service.addConnector(connector);

        //配置DispatcherServlet
        String dispatcherServletName = "dispatcher";
        tomcat.addServlet(contextPath, dispatcherServletName, new DispatcherServlet());
        context.addServletMappingDecoded("/*", dispatcherServletName);

        try {
            //启动服务并阻塞住
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
