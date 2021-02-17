package framework.protocol.http;

import framework.protocol.Invocation;
import framework.register.LocalRegister;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * HttpServerHandler 具体的请求逻辑处理，解耦Servlet容器处理流程和具体的service请求逻辑
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class HttpServerHandler {

    public void handle(ServletRequest req, ServletResponse resp) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            /**
             * 从req 中解析出远程调用需要的所有参数(服务名称信息，方法名称信息，方法参数信息，方法参数实体，版本号 等)
             * 以上信息可抽象成Invocation 对象
             *
             * 通过invocation+本地路由 获取具体的接口实现
             */
            StringBuffer jb = new StringBuffer();
            String line = null;
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            String reqBodyStr = jb.toString();
            System.out.println("req body ==>" + reqBodyStr);

            Invocation invocation = objectMapper.readValue(reqBodyStr, Invocation.class);

            //反射调用
            Class implCls = LocalRegister.find(invocation.getInvokeServerName());
            Method methodIns = implCls.getMethod(invocation.getInvokeMethodName(), invocation.getParamTypes());
            //todo：此处为了简便，省略了其实也需要对结果的描述信息和对结果的序列化操作
            String res = (String) methodIns.invoke(implCls.newInstance(), invocation.getParamArgs());

            //结果输出
            IOUtils.write(res, resp.getOutputStream());


        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                NoSuchMethodException e) {
            e.printStackTrace();
        } catch (
                IllegalAccessException e) {
            e.printStackTrace();
        } catch (
                InstantiationException e) {
            e.printStackTrace();
        } catch (
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
