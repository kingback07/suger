package org.kingback.suger.demo.demo4InnerCall;

import com.alibaba.fastjson.JSONObject;
import com.facebook.presto.jdbc.internal.okhttp3.HttpUrl;
import com.facebook.presto.jdbc.internal.okhttp3.OkHttpClient;
import com.facebook.presto.jdbc.internal.okhttp3.Request;
import com.facebook.presto.jdbc.internal.okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InnerCallApplication {

    private static class clientHandler {
        private static OkHttpClient sinletonClient = new OkHttpClient().newBuilder()
                .readTimeout(180, TimeUnit.SECONDS)
                .build();
    }

    private static OkHttpClient client = clientHandler.sinletonClient;

    public static void main(String args[]) throws InterruptedException {
        int productId = 451;
        int expId = 1173;
        long time = System.currentTimeMillis();

        String appSecret = "FdN4SP26sUkBVqGk";
        String sign = SignUtil.generateSignature(productId, appSecret, time);

        String unit = "1212112";
        String grpName = "c";

        String baseQueryStr = "http://ab-rest.xxxxxxx-cloud.com/open-api/experiment/addUnitToGrp?expId=" + expId + "&unit=" + unit + "&grpName=" + grpName + "&time=" + time + "&sign=" + sign;

        HttpUrl getUrl = HttpUrl.parse(baseQueryStr).newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(getUrl)
                .get()
                .build();
        JSONObject jo = getResponse(request);
        System.out.println(jo.toJSONString());


        String AbTestQuery = "http://abapi-test.xxxxxxx-cloud.com/api/get_treatment?unit=" + unit + "&utype=guid&prdid=" + productId + "&expid=" + expId;

        HttpUrl AbTestUrl = HttpUrl.parse(AbTestQuery).newBuilder()
                .build();
        Request AbTestReq = new Request.Builder()
                .url(AbTestUrl)
                .get()
                .build();
        JSONObject AbTestResp = getResponse(AbTestReq);
        System.out.println("AB 测试结果:" + AbTestResp.toJSONString());

    }

    public static JSONObject getResponse(Request req) {
        Response response;
        try {
            response = client.newCall(req).execute();
            String responseBody = response.body().string();
//            logger.info("response-->"+responseBody);
            if (response.isSuccessful()) {
                JSONObject data = JSONObject.parseObject(responseBody);
                return data;
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }
}