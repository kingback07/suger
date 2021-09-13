package org.kingback.suger.demo.demo4InnerCall;

import java.text.MessageFormat;
import java.util.concurrent.locks.ReentrantLock;

public class InnerCallApplication {

//    private static class clientHandler {
//        private static OkHttpClient sinletonClient = new OkHttpClient().newBuilder()
//                .readTimeout(180, TimeUnit.SECONDS)
//                .build();
//    }
//
//    private static OkHttpClient client = clientHandler.sinletonClient;

    public static void main(String args[]) throws InterruptedException {

//        ReentrantLock
//        int productId = 451;
//        String appSecret = "FdN4SP26sUkBVqGk";
//        int expId = 1173;

//        int productId = 455;
//        int expId=1245;
//        String appSecret = "EmRCOR96DPrshLQU";
//        long time = System.currentTimeMillis();
//
//        String sign = SignUtil.generateSignature(productId, appSecret, time);
//
//        String unit = "131321234";
//        String grpName = "b";
//
//        String baseQueryStr = "http:/localhost:8080/open-api/experiment/addUnitToGrp?expId=" + expId + "&unit=" + unit + "&grpName=" + grpName + "&time=" + time + "&sign=" + sign;
//
//        HttpUrl getUrl = HttpUrl.parse(baseQueryStr).newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(getUrl)
//                .get()
//                .build();
//        JSONObject jo = getResponse(request);
//        System.out.println(jo.toJSONString());
//
//
//        String AbTestQuery = "http://localhost:8080/api/get_treatment?unit=" + unit + "&utype=guid&prdid=" + productId + "&expid=" + expId;
//
//        HttpUrl AbTestUrl = HttpUrl.parse(AbTestQuery).newBuilder()
//                .build();
//        Request AbTestReq = new Request.Builder()
//                .url(AbTestUrl)
//                .get()
//                .build();
//        JSONObject AbTestResp = getResponse(AbTestReq);
//        System.out.println("AB 测试结果:" + AbTestResp.toJSONString());

//        String baseQueryStr = "http://localhost:8080/open-api/experiment/getExpList?prdId=" + productId + "&time=" + time + "&sign=" + sign;
//        HttpUrl getUrl = HttpUrl.parse(baseQueryStr).newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url(getUrl)
//                .get()
//                .build();
//        JSONObject jo = getResponse(request);
//        System.out.println(jo.toJSONString());

    }

//    public static JSONObject getResponse(Request req) {
//        Response response;
//        try {
//            response = client.newCall(req).execute();
//            String responseBody = response.body().string();
////            logger.info("response-->"+responseBody);
//            if (response.isSuccessful()) {
//                JSONObject data = JSONObject.parseObject(responseBody);
//                return data;
//            }
//        } catch (IOException e) {
//            return null;
//        }
//        return null;
//    }
}
