package com.touyuanren.perfectplay.data;

import com.touyuanren.perfectplay.bean.BaseBean;
import com.touyuanren.perfectplay.bean.LoginBean;
import com.touyuanren.perfectplay.bean.requestbean.LoginRequestBean;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.LoginContract;

import rx.Observable;

/**
 * Created by Liang on 2017/10/13 0013.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String pwd) {
        LoginRequestBean param = new LoginRequestBean();
        param.setEmail(phone);
        param.setPassword(pwd);
        return mApiService.login(param);
    }



}
