package com.github.lyrric.util;

import com.github.lyrric.model.HttpResult;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;

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

    public HttpClient(String confGroupId, String confDataId, String confServerHost) {
        this.confGroupId = confGroupId;
        this.confDataId = confDataId;
        this.confServerHost = confServerHost;
    }


    /**
     * 请求配置
     * @return
     */
    public HttpResult getConfig() throws IOException {
        final String url = confServerHost + "/api/conf/getConfig";
        httpGet(url);
        return null;
    }
    /**
     * 获取配置上一次的修改时间
     * @return
     */
    public Date getModifiedTime(){
        final String url = confServerHost + "/api/conf/getConfig";
        return null;
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    @SuppressWarnings("all")
    private String httpGet(String url) throws IOException {
        HttpUrl.Builder param = HttpUrl.parse(url).newBuilder();
        param.addQueryParameter("confGroupId", confGroupId);
        param.addQueryParameter("confDataId", confDataId);
        Request request = new Request.Builder()
                .url(param.build())
                .get()
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        String jsonStr = response.body().string();
        System.out.println(jsonStr);
        return jsonStr;
    }
}
