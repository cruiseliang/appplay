package com.touyuanren.perfectplay.presenter;

import android.util.Log;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.data.RecommendModel;
import com.touyuanren.perfectplay.presenter.contract.RecommendContract;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {
    @Inject
    public RecommendPresenter(RecommendContract.View view, RecommendModel recommendModel) {
        super(view, recommendModel);
    }


    public void requestDatas() {
//        mView.showLoading();
//        mModel.getApps(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                mView.dismissLoading();
//                if (response != null) {
//                    mView.showResult(response.body().getDatas());
//                } else {
//                    mView.showNoData();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//                mView.dismissLoading();
//                mView.showError(t.getMessage());
//            }
//        });


        mModel.getApps().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<PageBean<AppInfo>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        //订阅之前，初始化数据
                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        //结束
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> pageBean) {
                        //处理数据
                        if (pageBean != null) {
                            Log.e("ddd",pageBean.getDatas().size()+"");
                            mView.showResult(pageBean.getDatas());
                        } else {
                            mView.showNoData();
                        }
                    }
                });
    }
}
