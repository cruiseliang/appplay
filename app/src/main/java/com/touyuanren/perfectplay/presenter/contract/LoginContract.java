package com.touyuanren.perfectplay.presenter.contract;

import com.touyuanren.perfectplay.bean.BaseBean;
import com.touyuanren.perfectplay.bean.LoginBean;
import com.touyuanren.perfectplay.ui.BaseView;

import rx.Observable;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public interface LoginContract {

    public interface ILoginModel {

        Observable<BaseBean<LoginBean>> login(String phone, String pwd);

    }


    public interface LoginView extends BaseView {


        void checkPhoneError();

        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
//        void loginError(String msg);

    }
}
