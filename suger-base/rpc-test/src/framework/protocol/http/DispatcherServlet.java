package framework.protocol.http;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * 参考SpringMVC，通过DispatcherServlet 来统一派发请求
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class DispatcherServlet extends HttpServlet {

    private HttpServerHandler handler = new HttpServerHandler();

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        System.out.println("handle the request");
        handler.handle(req, resp);
    }
}
