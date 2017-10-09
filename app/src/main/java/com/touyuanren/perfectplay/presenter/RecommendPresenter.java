package com.touyuanren.perfectplay.presenter;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.data.RecommendModel;
import com.touyuanren.perfectplay.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class RecommendPresenter implements RecommendContract.Presenter {
    private RecommendModel mModel;
    private RecommendContract.View mView;

    public RecommendPresenter(RecommendContract.View mView) {
        this.mView = mView;
        mModel = new RecommendModel();
    }

    @Override
    public void requestDatas() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                mView.dismissLoading();
                if (response!=null) {
                    mView.showResult(response.body().getDatas());
                }else {
                    mView.showNoData();
                }
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                mView.dismissLoading();
                mView.showError(t.getMessage());
            }
        });
    }
}
