package com.touyuanren.perfectplay.presenter;

import android.Manifest;
import android.app.Activity;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.touyuanren.perfectplay.bean.IndexBean;
import com.touyuanren.perfectplay.common.rx.RxHttpResponseCompat;
import com.touyuanren.perfectplay.common.rx.Subscriber.ProgressSubcriber;
import com.touyuanren.perfectplay.data.AppInfoModel;
import com.touyuanren.perfectplay.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.View> {


    @Inject
    public RecommendPresenter(AppInfoContract.View view, AppInfoModel appInfoModel) {
        super(view, appInfoModel);
    }

    public void requsetPermission() {
        RxPermissions permission = new RxPermissions((Activity) context);
        permission.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    //授权成功
                    mView.onRequestPermissionSuccess();
                } else {
                    //授权失败
                    mView.onRequestPermissionSError();
                }
            }
        });
    }

    public void requestDatas() {
//        mModel.getApps().compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult()).
//                subscribe(new ProgressSubcriber<PageBean<AppInfo>>(context, mView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        if (appInfoPageBean != null) {
//
//                            mView.showResult(appInfoPageBean.getDatas());
//                        } else {
//                            mView.showNoData();
//                        }
//                    }
//                });
        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult()).
                subscribe(new ProgressSubcriber<IndexBean>(context, mView) {
                    @Override
                    public void onNext(IndexBean indexBeanBaseBean) {
                        mView.showResult(indexBeanBaseBean);
                    }
                });
//        mModel.getApps().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Subscriber<PageBean<AppInfo>>() {
//                    @Override
//                    public void onStart() {
//                        super.onStart();
//                        //订阅之前，初始化数据
//                        mView.showLoading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        //结束
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.dismissLoading();
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> pageBean) {
//                        //处理数据
//                        if (pageBean != null) {
//                            Log.e("ddd",pageBean.getDatas().size()+"");
//                            mView.showResult(pageBean.getDatas());
//                        } else {
//                            mView.showNoData();
//                        }
//                    }
//                });
    }


}
