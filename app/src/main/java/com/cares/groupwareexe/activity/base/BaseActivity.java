package com.cares.groupwareexe.activity.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Desc 所有activity的父类,提供公用的行为
 * @作者 Mark
 * @时间 2019-12-03
 * @EMail 2285581945@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 输出日志，所需的tag
     */
    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
        setContentView(getLayoutId());
        setContent();
    }

    protected abstract void setContent();

    protected abstract int getLayoutId();
}
