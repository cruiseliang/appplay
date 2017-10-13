package com.touyuanren.perfectplay.ui;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public interface BaseView {
    void showLoading();

    void dismissLoading();
    void  showError(String msg);
}
