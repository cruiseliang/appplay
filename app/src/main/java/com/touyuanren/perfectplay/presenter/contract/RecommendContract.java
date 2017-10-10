package com.touyuanren.perfectplay.presenter.contract;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.ui.BaseView;

import java.util.List;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public interface RecommendContract {
    interface View extends BaseView {
        void showNoData();
        void showResult(List<AppInfo> datas);
        void showError(String msg);
    }

}
