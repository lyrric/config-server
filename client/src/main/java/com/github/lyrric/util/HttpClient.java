package com.github.lyrric.util;

import com.alibaba.fastjson.JSONObject;
import com.github.lyrric.model.Config;
import com.github.lyrric.model.HttpResult;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
public class HttpClient {

    /**
     * 服务host
     */
    private String confServerHost;
    /**
     * confGroupId
     */
    private String confGroupId;
    /**
     * confDataId
     */
    private String confDataId;
    /**
     * confAppKey
     */
    private String confAppKey;
    /**
     * 请求客户端
     */
    private OkHttpClient okHttpClient;

    public HttpClient(String confGroupId, String confDataId, String confServerHost, String confAppKey, Integer reqTimeout) {
        this.confGroupId = confGroupId;
        this.confDataId = confDataId;
        this.confServerHost = confServerHost;
        this.confAppKey = confAppKey;
        int timeOut = reqTimeout==null?2000:reqTimeout;
        okHttpClient = new OkHttpClient.Builder()
                .callTimeout(timeOut,  TimeUnit.MILLISECONDS)
                .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
                .build();
    }


    /**
     * 请求配置
     * @return
     */
    public Config getConfig() throws IOException {
        final String url = confServerHost + "/api/remote/conf/get";
        return JSONObject.parseObject(httpGet(url), Config.class);
    }
    /**
     * 获取配置上一次的修改时间
     * @return
     */
    public Date getModifiedTime() throws IOException {
        final String url = confServerHost + "/api/remote/conf/modified-time";
        return JSONObject.parseObject(httpGet(url), Date.class);
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    @SuppressWarnings("all")
    private String httpGet(String url) throws RuntimeException, IOException {
        HttpUrl.Builder param = HttpUrl.parse(url).newBuilder();
        param.addQueryParameter("confGroupId", confGroupId);
        param.addQueryParameter("confDataId", confDataId);
        param.addQueryParameter("confAppKey", confAppKey);
        Request request = new Request.Builder()
                .url(param.build())
                .get()
                .header("Connection", "keep-alive")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String jsonStr = response.body().string();
        HttpResult result = JSONObject.parseObject(jsonStr, HttpResult.class);
        if(!result.getSuccess()){
            throw new RuntimeException(result.getErrMsg());
        }
        return JSONObject.toJSONString(result.getData());
    }
}
