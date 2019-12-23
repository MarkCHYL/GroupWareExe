package com.cares.groupwareexe.application;


import android.Manifest;
import android.app.Application;
import android.os.Environment;


import androidx.multidex.MultiDex;

import com.cares.groupwareexe.utils.PermissionUtils;
import com.mark.sdklib.log.MarkLog;

/**
 * @Desc 程序主入口
 * @作者 Mark
 * @时间 2019-12-02
 * @EMail 2285581945@qq.com
 */
public class ImoocApplication extends Application {

    public static ImoocApplication mImoocApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mImoocApplication = this;
        MultiDex.install(this);
        initLog();
    }

    /**
     * 初始化日期管理工具
     */
    private void initLog() {
        if (PermissionUtils.lacksPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/groupWarelog";
            MarkLog.getInstance() // 单例获取LogCook实例
                    .setLogPath(logPath) //设置日志保存路径
                    .setLogName("test.log") //设置日志文件名
                    .isOpen(true)  //是否开启输出日志
                    .isSave(false)  //是否保存日志
                    .initialize(); //完成初始化Crash监听
        }
    }

    /**
     * 单列模式
     *
     * @return
     */
    public static ImoocApplication getInstance() {
        return mImoocApplication;
    }
}
