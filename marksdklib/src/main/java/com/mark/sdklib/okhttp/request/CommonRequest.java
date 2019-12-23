package com.mark.sdklib.okhttp.request;

import android.util.Log;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019-12-09
 * @EMail 2285581945@qq.com
 */
public class CommonRequest {

    /**
     * @param url
     * @param params
     * @return 返回一个创建好的Request对象, post类型请求
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求参数，遍历添加到我们的请求构建类中
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        //通过请求构建类的build方法获取到真正的请求对象
        FormBody mFormBody = mFormBodyBuild.build();
        Log.d("markokhttp", "baseurl------>" + url);
        Log.d("markokhttp", "body------>" + mFormBody.toString());
        return new Request.Builder()
                .url(url)
                .post(mFormBody)
                .build();
    }


    /**
     * @param url
     * @param params
     * @return 通过传入的参数返回一个get 类型的请求
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry :
                    params.urlParams.entrySet()) {
                //将请求参数，遍历添加到我们的请求构建类中
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        String urlStr = urlBuilder.substring(0, urlBuilder.length() - 1);
        Log.d("markokhttp", "baseurl------>" + urlStr.toString());
        return new Request.Builder()
                .url(urlStr)
                .get()
                .build();
    }
}
