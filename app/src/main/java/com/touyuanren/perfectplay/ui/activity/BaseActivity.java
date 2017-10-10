package com.touyuanren.perfectplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.touyuanren.perfectplay.AppApplication;
import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Liang on 2017/10/10 0010.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Unbinder unbinder;
    private AppApplication mApplication;
    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        unbinder = ButterKnife.bind(this);
        mApplication = (AppApplication) getApplication();
        setActivityComponent(mApplication.getAppComponent());
        init();
    }

    public abstract int setLayout();

    public abstract void setActivityComponent(AppComponent appComponent);

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
        }
    }
}
