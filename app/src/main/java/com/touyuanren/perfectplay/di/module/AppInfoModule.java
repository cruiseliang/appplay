package com.touyuanren.perfectplay.di.module;

import com.touyuanren.perfectplay.data.AppInfoModel;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2017/10/9 0009.
 */
@Module
public class AppInfoModule {
    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView() {
        return mView;
    }
    @Provides
    public AppInfoModel privodeModel(ApiService apiService){

        return  new AppInfoModel(apiService);
    }

}
