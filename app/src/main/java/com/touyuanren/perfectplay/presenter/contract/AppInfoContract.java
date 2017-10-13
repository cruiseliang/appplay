package com.touyuanren.perfectplay.presenter.contract;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.IndexBean;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.ui.BaseView;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public interface AppInfoContract {
    interface View extends BaseView {

        void showResult(IndexBean mIndexBean);


        void onRequestPermissionSuccess();

        void onRequestPermissionSError();
    }

    interface AppInfoView extends BaseView {

        void showResult(PageBean<AppInfo> appInfoPageBean);

        void onLoadComplete();

    }
}
