package com.cares.groupwareexe.utils;

import android.content.Context;

import com.cares.groupwareexe.model.recommand.BaseRecommandModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019-12-19
 * @EMail 2285581945@qq.com
 */
public class Utils {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
    }

    //为ViewPager结构化数据
    public static ArrayList<BaseRecommandModel.DataBean.ListBean> handleData(BaseRecommandModel.DataBean.ListBean value) {
        ArrayList<BaseRecommandModel.DataBean.ListBean> values = new ArrayList<>();
        String[] titles = value.getTitle().split("@");
        String[] infos = value.getInfo().split("@");
        String[] prices = value.getPrice().split("@");
        String[] texts = value.getText().split("@");
        List<String> urls = value.getUrl();
        int start = 0;
        for (int i = 0; i < titles.length; i++) {
            BaseRecommandModel.DataBean.ListBean tempValue = new BaseRecommandModel.DataBean.ListBean();
            tempValue.setTitle(titles[i]);
            tempValue.setInfo(infos[i]);
            tempValue.setPrice(prices[i]);
            tempValue.setText(texts[i]);
            tempValue.setUrl(extractData(urls, start, 3));
            start += 3;

            values.add(tempValue);
        }
        return values;
    }

    private static ArrayList<String> extractData(List<String> source, int start, int interval) {
        ArrayList<String> tempUrls = new ArrayList<>();
        for (int i = start; i < start + interval; i++) {
            tempUrls.add(source.get(i));
        }
        return tempUrls;
    }
}
