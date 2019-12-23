package com.mark.sdklib.okhttp.exception;

/**
 * @Desc 自定义异常类
 * @作者 Mark
 * @时间 2019-12-06
 * @EMail 2285581945@qq.com
 */
public class OkHttpException {
    private int ecode;//异常码
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public void setEcode(int ecode) {
        this.ecode = ecode;
    }

    public Object getEmsg() {
        return emsg;
    }

    public void setEmsg(Object emsg) {
        this.emsg = emsg;
    }

    @Override
    public String toString() {
        return "OkHttpException{" +
                "ecode=" + ecode +
                ", emsg=" + emsg +
                '}';
    }
}
