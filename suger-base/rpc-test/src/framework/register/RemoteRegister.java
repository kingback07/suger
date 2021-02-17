package framework.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.common.URL4Rpc;

/**
 * 模拟远程注册中心
 * nacos,zk,redis,eureka,etcd ……
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class RemoteRegister {
    private final static String FILE_PATH = "/Users/wangyang09/Documents/register.txt";
    private static HashMap<String, List<URL4Rpc>> remoteRegisterCenter = new HashMap<String, List<URL4Rpc>>();

    public static void regist(String serviceName, URL4Rpc url) {
        if (!remoteRegisterCenter.containsKey(serviceName)) {
            List<URL4Rpc> urlList = new ArrayList<URL4Rpc>();
            remoteRegisterCenter.put(serviceName, urlList);
        }
        remoteRegisterCenter.get(serviceName).add(url);
        save();
    }

    public static List<URL4Rpc> get(String serviceName) {
        remoteRegisterCenter.clear();
        remoteRegisterCenter.putAll(read());
        return remoteRegisterCenter.get(serviceName);
    }

    //远程服务作为单独进程时，需要把服务注册信息进行本地持久化
    private static void save() {
        try {
            File registerFile = new File(FILE_PATH);
            if (!registerFile.exists()) {
                registerFile.createNewFile();
            }
            FileOutputStream stream = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
            objectOutputStream.writeObject(remoteRegisterCenter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL4Rpc>> read() {
        try {
            FileInputStream stream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(stream);
            return (Map<String, List<URL4Rpc>>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
