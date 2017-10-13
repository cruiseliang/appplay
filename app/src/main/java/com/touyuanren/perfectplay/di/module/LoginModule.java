package com.touyuanren.perfectplay.di.module;

import com.touyuanren.perfectplay.data.LoginModel;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2017/10/10 0010.
 */
@Module
public class LoginModule {
    private LoginContract.LoginView mView;

    public LoginModule(LoginContract.LoginView mView) {
        this.mView = mView;
    }

    @Provides
    public LoginContract.LoginView provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel privodeModel(ApiService apiService){

        return  new LoginModel(apiService);
    }

}
