package com.cares.groupwareexe.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cares.groupwareexe.R;
import com.cares.groupwareexe.adapter.CourseAdapter;
import com.cares.groupwareexe.fragment.base.BaseFragment;
import com.cares.groupwareexe.model.recommand.BaseRecommandModel;
import com.cares.groupwareexe.network.RequestCenter;
import com.mark.sdklib.log.MarkLog;
import com.mark.sdklib.okhttp.listener.DisposeDataListener;

import java.util.ArrayList;


/**
 * @Desc 首页Fragment
 * @作者 Mark
 * @时间 2019-12-02
 * @EMail 2285581945@qq.com
 */
public class HomeFragment extends BaseFragment {

    /**
     * UI
     */
    private ListView mListView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;
    
    /**
     * data
     */
    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandData;

    @Override
    protected void initView(View rootView) {
        mListView = rootView.findViewById(R.id.list_view);
        mQRCodeView = rootView.findViewById(R.id.qrcode_view);
        mCategoryView = rootView.findViewById(R.id.category_view);
        mSearchView = rootView.findViewById(R.id.search_view);
        mLoadingView = rootView.findViewById(R.id.loading_view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    /**
     * 发送推荐产品请求
     */
    private void requestRecommandData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                MarkLog.d(TAG, "onSuccess: " + responseObj.toString());
                mRecommandData = (BaseRecommandModel) responseObj;
                //更新UI
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                MarkLog.d(TAG, "onFailure: " + reasonObj.toString());
            }
        });
    }

    //显示请求成功UI
    private void showSuccessView() {
        if (mRecommandData.getData().getList() != null && mRecommandData.getData().getList().size() > 0) {
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            //为listview添加头
            mAdapter = new CourseAdapter(mContext, (ArrayList<BaseRecommandModel.DataBean.ListBean>) mRecommandData.getData().getList());
            mListView.setAdapter(mAdapter);
           
        } else {
            showErrorView();
        }
    }

    private void showErrorView() {
    }
}
