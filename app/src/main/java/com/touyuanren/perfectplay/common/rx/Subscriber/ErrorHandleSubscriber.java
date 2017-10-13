package com.touyuanren.perfectplay.common.rx.Subscriber;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.touyuanren.perfectplay.common.exception.BaseException;
import com.touyuanren.perfectplay.common.rx.RxErrorHandler;
import com.touyuanren.perfectplay.ui.activity.LoginActivity;

/**
 * Created by Liang on 2017/10/11 0011.
 */

public abstract class ErrorHandleSubscriber<T> extends BaseSubscriber<T> {

    protected RxErrorHandler mErrorHandler = null;
    protected Context mContext;

    public ErrorHandleSubscriber(Context context) {

        this.mContext = context;


        mErrorHandler = new RxErrorHandler(mContext);

    }

    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handleError(e);
        if (baseException == null) {
            e.printStackTrace();
            Log.d("ErrorHandlerSubscriber", e.getMessage());
        } else {
            mErrorHandler.showErrorMessage(baseException);
            if (baseException.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
            }

        }
    }

    protected void toLogin() {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

    ;

}
