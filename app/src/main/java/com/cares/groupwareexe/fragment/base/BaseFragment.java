package com.cares.groupwareexe.fragment.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Desc   所有Fragment的公共的行为和事件
 * @作者 Mark
 * @时间 2019-12-02
 * @EMail 2285581945@qq.com
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mContext;
    /**
     * 输出日志，所需的tag
     */
    public String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView =  inflater.inflate(getLayoutId(),null,false);
       initView(rootView);
       return rootView;
    }

    protected abstract void initView(View rootView);

    protected abstract int getLayoutId();
}
