package framework.common;

import java.io.Serializable;

/**
 * @author wangyang09
 * Created on 2021-02-17
 */
public class URL4Rpc implements Serializable {

    private String hostname;
    private Integer port;

    public URL4Rpc(String hostname, Integer port) {
        this.hostname = hostname;
        this.port=port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
