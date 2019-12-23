package com.mark.sdklib.okhttp.client;

import android.util.Log;

import com.mark.sdklib.okhttp.exception.OkHttpException;
import com.mark.sdklib.okhttp.https.HttpsUtils;
import com.mark.sdklib.okhttp.listener.DisposeDataHandle;
import com.mark.sdklib.okhttp.listener.DisposeDataListener;
import com.mark.sdklib.okhttp.request.CommonRequest;
import com.mark.sdklib.okhttp.response.CommonJsonBack;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Desc 请求的发送，请求参数的配置，https支持
 * @作者 Mark
 * @时间 2019-12-09
 * @EMail 2285581945@qq.com
 */
public class CommonOkHttpClient {


    //设置超时时间 30S
    private static final int TIME_OUT = 30;

    //OkHttpClient请求客户端
    private static OkHttpClient mOkHttpClient;

    //为我们 Client 配置参数
    static {
        //创建我们的Client构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //设置连接超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        //设置读写超时时间
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);

        //允许请求转发重定向
        okHttpBuilder.followRedirects(true);

        //添加Https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                // 无论你是什么主机，这里设置为返回True
                return true;
            }
        });

        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory(),
                HttpsUtils.getTrustManager());
//        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        //生成client对象
        if (mOkHttpClient == null){
            synchronized (CommonOkHttpClient.class){
                if (mOkHttpClient==null){
                    mOkHttpClient = okHttpBuilder.build();
                }
            }
        }
    }

    /**
     * 发送具体的http,https请求
     *
     * @param request
     * @param commonJsonBack
     * @return
     */
    public static Call sendRequest(Request request, CommonJsonBack commonJsonBack) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonJsonBack);

        return call;
    }

    private void test() {
        CommonOkHttpClient.sendRequest(CommonRequest.
                createGetRequest("http://op.juhe.cn/onebox/weather/query", null), new CommonJsonBack(
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        String json = responseObj.toString();
                        Log.d("测试",json);
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        OkHttpException exception = (OkHttpException) reasonObj;
                        Log.d("测试",exception.toString());
                    }
                })
        ));
    }
}
