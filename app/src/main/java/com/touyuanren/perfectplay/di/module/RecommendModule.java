package com.touyuanren.perfectplay.di.module;

import android.app.ProgressDialog;

import com.touyuanren.perfectplay.data.RecommendModel;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.RecommendContract;
import com.touyuanren.perfectplay.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2017/10/9 0009.
 */
@Module
public class RecommendModule {
    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public RecommendContract.View provideView() {
        return mView;
    }
    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view){

        return new ProgressDialog(((RecommendFragment)view).getActivity());
    }
    @Provides
    public RecommendModel privodeModel(ApiService  apiService){

        return  new  RecommendModel(apiService);
    }
}
