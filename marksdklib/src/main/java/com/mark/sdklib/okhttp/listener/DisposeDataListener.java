package com.mark.sdklib.okhttp.listener;


/**
 * @Desc  业务逻辑层真正处理的地方，包括java层异常和业务层异常
 * @作者 Mark
 * @时间 2019-12-09
 * @EMail 2285581945@qq.com
 */
public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     * @param responseObj
     */
    public void onSuccess(Object responseObj);


    /**
     * 请求失败回调时间处理
     * @param reasonObj
     */
    public void onFailure(Object reasonObj);
}
