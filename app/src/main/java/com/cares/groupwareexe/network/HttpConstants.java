package com.cares.groupwareexe.network;

/**
 * @Desc 所有请求相关地址
 * @作者 Mark
 * @时间 2019-12-18
 * @EMail 2285581945@qq.com
 */
public class HttpConstants {

    private static final String ROOT_URL = "http://imooc.com/api";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "/product/course_detail.php";

}
