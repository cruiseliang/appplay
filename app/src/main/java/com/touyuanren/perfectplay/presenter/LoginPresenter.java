package com.touyuanren.perfectplay.presenter;

import com.hwangjr.rxbus.RxBus;
import com.touyuanren.perfectplay.bean.LoginBean;
import com.touyuanren.perfectplay.common.Constant;
import com.touyuanren.perfectplay.common.rx.RxHttpResponseCompat;
import com.touyuanren.perfectplay.common.rx.Subscriber.ErrorHandleSubscriber;
import com.touyuanren.perfectplay.common.util.ACache;
import com.touyuanren.perfectplay.common.util.VerificationUtils;
import com.touyuanren.perfectplay.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by Liang on 2017/10/13 0013.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {

    @Inject
    public LoginPresenter(LoginContract.LoginView loginView, LoginContract.ILoginModel iLoginModel) {
        super(loginView, iLoginModel);
    }

    public void login(String phone, String pwd) {
        if (!VerificationUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
            return;
        } else {
            mView.checkPhoneSuccess();
        }
        mModel.login(phone, pwd).compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new ErrorHandleSubscriber<LoginBean>(context) {
                    @Override
                    public void onStart() {

                        mView.showLoading();
                    }

                    @Override
                    public void onCompleted() {

                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.loginSuccess(loginBean);
                        saveUser(loginBean);
                        RxBus.get().post(loginBean.getUser());
                    }
                });
    }

    private void saveUser(LoginBean bean) {

        ACache aCache = ACache.get(context);

        aCache.put(Constant.TOKEN, bean.getToken());
        aCache.put(Constant.USER, bean.getUser());
    }

}
