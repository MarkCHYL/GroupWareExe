package com.mark.sdklib.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.mark.sdklib.okhttp.exception.OkHttpException;
import com.mark.sdklib.okhttp.listener.DisposeDataHandle;
import com.mark.sdklib.okhttp.listener.DisposeDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Desc 专门处理JSON的回调响应
 * @作者 Mark
 * @时间 2019-12-09
 * @EMail 2285581945@qq.com
 */
public class CommonJsonBack implements Callback {
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private Handler mSendMsgHandler; //进行消息的转发
    private DisposeDataListener listener;
    private Class<?> mClass;

    public CommonJsonBack(DisposeDataHandle handle) {
        this.listener = handle.mListener;
        this.mClass = handle.mClass;
        this.mSendMsgHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure( Call call, final IOException ioexception) {
        mSendMsgHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    //请求正确的响应处理函数
    @Override
    public void onResponse(Call call,  Response response) throws IOException {
        final String result = response.body().string();
        mSendMsgHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理访问成功后的数据
     *
     * @param resultObj
     */
    private void handleResponse(String resultObj) {
        //为了保持代码的健壮性,判断数据
        if (resultObj == null && resultObj.trim().equals("")) {
            listener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        try {
            JSONObject result = new JSONObject(resultObj.toString());
            Log.d("markokhttp",resultObj.toString());
            Gson gson = new Gson();

            //开始尝试解析json
            //不需要解析，直接返回数据到应用
            if (mClass == null) {
                listener.onSuccess(resultObj);
            } else {
                //即需要将JOSN对象转化为实体对象
                Object obj = gson.fromJson(String.valueOf(result), mClass);
                //表明正确的转为了实体对象
                if (obj != null) {
                    listener.onSuccess(obj);
                } else {
                    //返回的不是合法的json
                    listener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (JSONException e) {
           listener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }
    }
}
