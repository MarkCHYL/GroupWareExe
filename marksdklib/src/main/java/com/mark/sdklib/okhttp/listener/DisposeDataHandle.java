package com.mark.sdklib.okhttp.listener;

/**
 * @Desc 数据回传到UI主线程
 * @作者 Mark
 * @时间 2019-12-09
 * @EMail 2285581945@qq.com
 */
public class DisposeDataHandle {

    public DisposeDataListener mListener = null;
    public Class<?> mClass = null;

    public DisposeDataHandle(DisposeDataListener mListener) {
        this.mListener = mListener;
    }

    public DisposeDataHandle(DisposeDataListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }
}
