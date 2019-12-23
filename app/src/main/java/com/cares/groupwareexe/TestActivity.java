package com.cares.groupwareexe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mark.sdklib.glide.GlideManager;

public class TestActivity extends AppCompatActivity {
    private ImageView imgOne, imgTwo,imgThree;
    private LinearLayout ll_test;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext = this;
        initVie();
        setContent();
    }

    private void setContent() {
        //方式一
        GlideManager.showImageView(mContext, R.drawable.errorimg,
                "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4144473397,1642841325&fm=26&gp=0.jpg", imgOne);
        //方式二
        GlideManager.showImageViewToCircle(mContext, R.drawable.errorimg,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2425727706,3350917988&fm=26&gp=0.jpg",
                imgTwo);

        //方式三
        GlideManager.showImageViewGone(mContext,
                "https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWKhhy/it/u=4144473397,1642841325&fm=26&gp=0.jpg", imgThree);

        //设置高斯模糊
        GlideManager.showImageViewBlur(mContext, R.drawable.errorimg,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1462333184,2196733460&fm=26&gp=0.jpg", ll_test);
    }

    private void initVie() {
        imgOne = findViewById(R.id.img_one);
        imgTwo = findViewById(R.id.img_two);
        imgThree = findViewById(R.id.img_three);
        ll_test = findViewById(R.id.ll_test);
    }
}
