package com.cares.groupwareexe.network;

import com.cares.groupwareexe.model.recommand.BaseRecommandModel;
import com.mark.sdklib.okhttp.client.CommonOkHttpClient;
import com.mark.sdklib.okhttp.listener.DisposeDataHandle;
import com.mark.sdklib.okhttp.listener.DisposeDataListener;
import com.mark.sdklib.okhttp.request.CommonRequest;
import com.mark.sdklib.okhttp.request.RequestParams;
import com.mark.sdklib.okhttp.response.CommonJsonBack;

/**
 * @Desc 网络请求管理
 * @作者 Mark
 * @时间 2019-12-18
 * @EMail 2285581945@qq.com
 */
public class RequestCenter {

    /**
     * 根据参数发送所有post请求
     */
    public static void postRequest(String url, RequestParams params,
                                   DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.sendRequest(CommonRequest.createPostRequest(url, params),
                new CommonJsonBack(new DisposeDataHandle(listener, clazz)));
    }

    /**
     * 首页数据
     */
    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }

}
