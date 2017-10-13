package com.touyuanren.perfectplay.common.rx.Subscriber;

import android.content.Context;

import com.touyuanren.perfectplay.common.util.ProgressDialogHandler;

/**
 * Created by Liang on 2017/10/11 0011.
 */

public abstract class ProgressDialogSubscriber<T> extends ErrorHandleSubscriber<T> implements ProgressDialogHandler.OnProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;
    public ProgressDialogSubscriber(Context context) {
        super(context);

        mProgressDialogHandler = new ProgressDialogHandler(mContext,true,this);
    }
    @Override
    public void onCancelProgress() {
        unsubscribe();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShowDialog())
            showProgressDialog();
    }

    @Override
    public void onCompleted() {
        if (isShowDialog())
            dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (isShowDialog())
            dismissProgressDialog();
    }

    protected boolean isShowDialog() {
        return true;
    }

    private void showProgressDialog() {
        mProgressDialogHandler.showProgressDialog();
    }

    private void dismissProgressDialog() {
        mProgressDialogHandler.dismissProgressDialog();
    }
}
