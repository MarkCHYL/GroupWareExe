package com.cares.groupwareexe.model.monitor;

import com.cares.groupwareexe.model.BaseModel;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019-12-19
 * @EMail 2285581945@qq.com
 */
public class Monitor extends BaseModel {

    /**
     * ver : 0
     * url : http://imooc.com/click??click=1
     */

    private String ver;
    private String url;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
