package com.touyuanren.perfectplay.common.rx.Subscriber;

import android.content.Context;

import com.touyuanren.perfectplay.common.exception.BaseException;
import com.touyuanren.perfectplay.ui.BaseView;


public  abstract  class ProgressSubcriber<T> extends ErrorHandleSubscriber<T>  {




    private BaseView mView;


    public ProgressSubcriber(Context context, BaseView view) {
        super(context);
        this.mView = view;

    }



    public boolean isShowProgress(){
        return true;
    }



    @Override
    public void onStart() {

        if(isShowProgress()){
            mView.showLoading();
        }

    }

    @Override
    public void onCompleted() {

            mView.dismissLoading();
    }

    @Override
    public void onError(Throwable e) {

        BaseException baseException =  mErrorHandler.handleError(e);
        mView.showError(baseException.getDisplayMessage());

    }

}
