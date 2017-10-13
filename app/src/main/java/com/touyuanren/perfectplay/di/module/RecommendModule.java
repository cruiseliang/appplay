package com.touyuanren.perfectplay.di.module;

import android.app.ProgressDialog;

import com.touyuanren.perfectplay.data.AppInfoModel;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.AppInfoContract;
import com.touyuanren.perfectplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2017/10/9 0009.
 */
@Module
public class RecommendModule {
    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.View provideView() {
        return mView;
    }
    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view){

        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
    @Provides
    public AppInfoModel privodeModel(ApiService  apiService){

        return  new AppInfoModel(apiService);
    }
}
