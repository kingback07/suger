package framework.protocol.http;

import framework.common.URL4Rpc;
import framework.protocol.Invocation;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URI;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 采用OKHttp 作为模拟发送端
 *
 * @author wangyang09
 * Created on 2021-02-17
 */
public class Client4Http {

    private static class clientHandler {
        private static OkHttpClient sinletonClient = new OkHttpClient().newBuilder()
                .readTimeout(180, TimeUnit.SECONDS)
                .build();
    }

    private static OkHttpClient client = clientHandler.sinletonClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    public String send(URL4Rpc url4Rpc, Invocation invocation) {
        try {
            MediaType JSON = MediaType.parse("application/json;charset=utf-8");
            String jsonStr = objectMapper.writeValueAsString(invocation);
            RequestBody requestBody = RequestBody.create(JSON, jsonStr);
            Request request = new Request.Builder()
                    .url(HttpUrl.get(new URI("http", null, url4Rpc.getHostname(), url4Rpc.getPort(), "/", null, null)))
                    .post(requestBody)
                    .build();

            Response response = client
                    .newCall(request)
                    .execute();

            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
