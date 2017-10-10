package com.touyuanren.perfectplay.presenter;

import com.touyuanren.perfectplay.ui.BaseView;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class BasePresenter<M, V extends BaseView> {
    protected V mView;
    protected M mModel;

    public BasePresenter(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }
}
