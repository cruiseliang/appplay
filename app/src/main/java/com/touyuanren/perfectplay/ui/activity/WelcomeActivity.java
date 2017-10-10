package com.touyuanren.perfectplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.common.Constant;
import com.touyuanren.perfectplay.common.util.ACache;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liang on 2017/10/10 0010.
 */

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.pathView)
    PathView mPathView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mPathView.getPathAnimator().delay(100)
                .interpolator(new AccelerateDecelerateInterpolator())
                .duration(4000).listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
            @Override
            public void onAnimationEnd() {
                jump();
            }
        }).start();
    }

    public void jump() {
        String isShowGuide =  ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);

        // 第一次启动进入引导页面
        if(null == isShowGuide){
            startActivity(new Intent(this,GuideActivity.class));

        }
        else{
            startActivity(new Intent(this,MainActivity.class));
        }
        this.finish();
    }
}
