package org.kingback.suger.demo.Demo4IpCity;


import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IpAddressLocation {

    public static void main(String[] args) throws IOException {


    }

    public static Map<String, CityInfo> cityInfoMap;

    static {
        cityInfoMap = new HashMap<>();
        //读取配置文件信息，构造Map<String,CityInfo>

        String basepath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//            System.out.println("resourcePath:"+basepath);
        String mapfilepath = basepath + "ipdata/cityInfoList";

        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mapfilepath)),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(":", 2);
                String key = names[0];
                String jsonStr = names[1];
                jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
                CityInfo cityInfo = JSONObject.parseObject(jsonStr, CityInfo.class);
                cityInfoMap.put(key, cityInfo);
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

    }

}
