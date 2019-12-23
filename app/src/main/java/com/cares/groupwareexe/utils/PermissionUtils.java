package com.cares.groupwareexe.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

/**
 * @Desc 权限管理工具
 * @作者 Mark
 * @时间 2019-12-11
 * @EMail 2285581945@qq.com
 */
public class PermissionUtils {

    /**
     * 判断是否缺少某个权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean lacksPermission(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 判断权限集合
     * permissions 权限数组
     * return true-表示没有改权限  false-表示权限已开启
     */
    public boolean lacksPermissions(Context mContexts, String[] mPermissions) {
        for (String permission : mPermissions) {
            if (lacksPermission(mContexts, permission)) {
                Log.e("TAG", "-------没有开启权限");
                return true;
            }
        }
        Log.e("TAG", "-------权限已开启");
        return false;

    }
}
