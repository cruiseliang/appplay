package com.touyuanren.perfectplay.ui.fragment;

import android.annotation.SuppressLint;

import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerAppInfoComponent;
import com.touyuanren.perfectplay.di.module.AppInfoModule;
import com.touyuanren.perfectplay.ui.adapter.AppInfoAdapter;


@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {





    private int categoryId;
    private int mFlagType;


    public CategoryAppFragment(int categoryId, int flagType){
        this.categoryId = categoryId;
        this.mFlagType = flagType;
    }



    public static CategoryAppFragment newInstance(int categoryId, int flagType){


        return  new CategoryAppFragment(categoryId,flagType);

    }


    @Override
    public void init() {


        mPresenter.requestCategoryApps(categoryId,page,mFlagType);
        initRecyclerView();

    }

    @Override
    int type() {
        return 0;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return  AppInfoAdapter.builder().showPosition(false).showBrief(true).showCategoryName(false).build();
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this))
                .build().injectCategoryAppFragment(this);
    }
}
