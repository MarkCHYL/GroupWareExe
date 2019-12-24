package com.cares.groupwareexe.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cares.groupwareexe.R;
import com.cares.groupwareexe.activity.base.BaseActivity;
import com.cares.groupwareexe.fragment.home.HomeFragment;
import com.cares.groupwareexe.fragment.home.MessageFragment;
import com.cares.groupwareexe.fragment.home.MineFragment;
import com.mark.sdklib.log.MarkLog;

/**
 * @Desc 首页管理（首页、消息、我的）
 * @作者 Mark
 * @时间 ${DATE}
 * @EMail 2285581945@qq.com
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgHome, imgMessage, imgMine;
    private TextView tvHome, tvMessage, tvMine;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private FrameLayout flContent;
    private LinearLayout ll_home, ll_message, ll_mine;

    @Override
    protected void setContent() {
        //初始化控件
        initView();
        //设置默认界面为首页
        select(0);

        initPermission();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android
                    .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @SuppressLint("ResourceAsColor")
    private void select(int i) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        initTabs();
        switch (i) {
            case 0:
                imgHome.setSelected(true);
                tvHome.setSelected(true);
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_content, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                imgMessage.setSelected(true);
                tvMessage.setSelected(true);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.fl_content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 2:
                imgMine.setSelected(true);
                tvMine.setSelected(true);
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.fl_content, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }

    @SuppressLint("ResourceAsColor")
    private void initTabs() {
        imgHome.setSelected(false);
        tvHome.setSelected(false);
        imgMessage.setSelected(false);
        tvMessage.setSelected(false);
        imgMine.setSelected(false);
        tvMine.setSelected(false);
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    private void initView() {
        imgHome = findViewById(R.id.img_home);
        tvHome = findViewById(R.id.tv_home);
        imgMessage = findViewById(R.id.img_massage);
        tvMessage = findViewById(R.id.tv_massage);
        imgMine = findViewById(R.id.img_mine);
        tvMine = findViewById(R.id.tv_mine);
        flContent = findViewById(R.id.fl_content);

        ll_home = findViewById(R.id.ll_home);
        ll_message = findViewById(R.id.ll_message);
        ll_mine = findViewById(R.id.ll_mine);
        ll_home.setOnClickListener(this);
        ll_message.setOnClickListener(this);
        ll_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                select(0);
                break;
            case R.id.ll_message:
                select(1);
                break;
            case R.id.ll_mine:
                select(2);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLog();
                    break;
                }
        }
    }

    private void initLog() {
        String logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/groupWarelog";
        MarkLog.getInstance() // 单例获取LogCook实例
                .setLogPath(logPath) //设置日志保存路径
                .setLogName("test.log") //设置日志文件名
                .isOpen(true)  //是否开启输出日志
                .isSave(true)  //是否保存日志
                .initialize(); //完成初始化Crash监听
    }
}
