package framework.protocol;

/**
 * 远程调用信息的抽象
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class Invocation {

    private String invokeServerName;
    private String invokeMethodName;
    private Class[] paramTypes;
    private Object[] paramArgs;

    public Invocation() {

    }

    public Invocation(String invokeServerName, String invokeMethodName, Class[] paramTypes, Object[] paramArgs) {
        this.invokeServerName = invokeServerName;
        this.invokeMethodName = invokeMethodName;
        this.paramTypes = paramTypes;
        this.paramArgs = paramArgs;
    }

    public String getInvokeServerName() {
        return invokeServerName;
    }

    public void setInvokeServerName(String invokeServerName) {
        this.invokeServerName = invokeServerName;
    }

    public String getInvokeMethodName() {
        return invokeMethodName;
    }

    public void setInvokeMethodName(String invokeMethodName) {
        this.invokeMethodName = invokeMethodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParamArgs() {
        return paramArgs;
    }

    public void setParamArgs(Object[] paramArgs) {
        this.paramArgs = paramArgs;
    }
}
