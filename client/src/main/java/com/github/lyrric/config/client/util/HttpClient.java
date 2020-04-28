package com.github.lyrric.config.client.util;

import com.alibaba.fastjson.JSONObject;
import com.github.lyrric.common.model.req.ReqConfigParam;
import com.github.lyrric.config.client.model.Config;
import com.github.lyrric.config.client.model.HttpResult;
import com.github.lyrric.config.client.properties.ConfigProperties;
import okhttp3.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
public class HttpClient {

    private ConfigProperties properties;
    /**
     * 请求客户端
     */
    private OkHttpClient okHttpClient;

    public HttpClient(ConfigProperties properties) {
        this.properties = properties;
        okHttpClient = new OkHttpClient.Builder()
                .callTimeout(properties.getReqTimeout(),  TimeUnit.MILLISECONDS)
                .connectTimeout(properties.getReqTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }


    /**
     * 请求配置
     * @return
     */
    public List<Config> getConfig() throws IOException {
        final String url = properties.getServerHost() + "/api/remote/conf/get";
        String json = initPostData();
        return JSONObject.parseArray(httpPost(url, json), Config.class);
    }
    /**
     * 获取配置上一次的修改时间
     * @return
     */
/*    public Map<String, Date> getModifiedTime() throws IOException {
        final String url = properties.getServerHost() + "/api/remote/conf/modified-time";
        String json = JSONArray.toJSONString(properties.getConfigs());
        return JSONObject.parseObject(httpPost(url, json), new TypeReference<Map<String, Date>>(){});
    }*/

    /**
     * 发送post请求
     * @param url
     * @return
     */
    @SuppressWarnings("all")
    private String httpPost(String url, String json) throws RuntimeException, IOException {
        RequestBody body = RequestBody.create(MediaType.get("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
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

    private String initPostData(){
        ReqConfigParam reqConfigParam = new ReqConfigParam();
        reqConfigParam.setGroupId(properties.getGroupId());
        reqConfigParam.setDataIds(Arrays.asList(properties.getDataIds()));
        return JSONObject.toJSONString(reqConfigParam);
    }
}
