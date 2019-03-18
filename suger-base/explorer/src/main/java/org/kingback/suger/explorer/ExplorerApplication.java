package org.kingback.suger.explorer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ExplorerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ExplorerApplication.class, args);
        String testStr="I Wanna to Learn More Tech";
        String res=testStr.substring(2,7);
        System.out.println(res);

        Map<String,String> resMap=new HashMap<String,String>();

        int n=Integer.MAX_VALUE;
        int x=n>>>4;

        System.out.println(n+":"+Integer.toBinaryString(n));


        char[] strArray=testStr.toCharArray();
        List<char[]> charlist= Arrays.asList(strArray);

        //ConcurrentHashMap
    }
}
