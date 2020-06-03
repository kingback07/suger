package org.kingback.suger.demo.demo4ReadCDNLog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class ReadDemo {
    public static void main(String[] args) throws ParseException {

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new GZIPInputStream(
                                    new FileInputStream("/Users/kingback/cdnLog/cj.maodou.com_2020-01-06.log.gz")), "iso-8859-1"));

            String line = null;

            while ((line = reader.readLine()) != null) {
//                String[] content=line.split(" ");
                String patternStr =
                        "(?<ip>\\d+\\.\\d+\\.\\d+\\.\\d+)(\\s+)" +
                                "(?<datetime>\\S+\\s+\\S+)(\\s+)" +
                                "(?<reqtime>\\d+\\.\\d+)(\\s+)" +
                                "(?<inbyte>\\S+)(\\s+)" +
                                "(?<outbyte>\\S+)(\\s+)" +
                                "(?<domain>\\w+\\.\\w+\\.\\w+)(\\s+)" +
                                "(?<method>\\S+)(\\s+)" +
                                "(?<url>[a-zA-z]+://[^\\s]+)(\\s+)" +
                                "(?<protocol>\\S+/\\S+)(\\s+)" +
                                "(?<type>\\S+/\\S+)(\\s+)" +
                                "(?<content>\\S+/\\S+)(\\s+)" +
                                "(?<referer>\\S+)(\\s+)" +
                                "(?<agent>(\\\").*?(\\\"))";
                Pattern pattern = Pattern.compile(patternStr);
                Matcher ma = pattern.matcher(line);
                StringBuffer buf = new StringBuffer("");
                if (ma.find()) {
                    System.out.println("ip->" + ma.group("ip"));
//                    System.out.println("domain->" + ma.group("domain"));
//                    System.out.println("contype->" + ma.group("contype"));
                    System.out.println("datetime->" + ma.group("datetime"));
                    System.out.println("reqtime->" + ma.group("reqtime"));
                    System.out.println("inbyte->" + ma.group("inbyte"));
                    System.out.println("outbyte->" + ma.group("outbyte"));
                    System.out.println("domain->" + ma.group("domain"));
                    System.out.println("method->" + ma.group("method"));
                    System.out.println("url->" + ma.group("url"));
                    System.out.println("protocol->" + ma.group("protocol"));
                    System.out.println("type->" + ma.group("type"));
                    System.out.println("content->" + ma.group("content"));
                    System.out.println("referer->" + ma.group("referer"));
                    System.out.println("agent->" + ma.group("agent"));

//                    String dateStr = ma.group("datetime").split(" ")[0];
//
//                    dateStr = dateStr.substring(1, dateStr.length());
//                    String[] dateTimeArray = dateStr.split(":");
//                    String dStr = dateTimeArray[0];
//                    dStr = dStr.substring(0, 2);
//                    String hourStr = dateTimeArray[1];
//                    String minStr = dateTimeArray[2];
//                    String secStr = dateTimeArray[3];

                    //对DateStr进行处理，年月可固定到 2020-01，日期
//                    String unitStringFomart = String.format("2020-01-%s %s:%s:%s", dStr, hourStr, minStr, secStr);
//
//                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
//                    Date dt = df.parse(unitStringFomart);
//                    System.out.println("realTime->" + dt.getTime());
//
//                    System.out.println("req->" + ma.group("req"));
//                    System.out.println("responsecode->" + ma.group("responsecode"));
//                    System.out.println("responsetime->" + ma.group("responsetime"));
//                    System.out.println("referurl->" + ma.group("referurl"));
//                    System.out.println("useragent->" + ma.group("useragent"));
                } else {
                    System.out.println("noparse:" + line);
                }
//                System.out.println(line);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
}
