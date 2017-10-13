package com.touyuanren.perfectplay.di.module;

import com.touyuanren.perfectplay.data.CategoryModel;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.CategoryContract;

import dagger.Module;
import dagger.Provides;





@Module
public class CategoryModule {



    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView view){
        this.mView = view;
    }





    @Provides
    public CategoryContract.CategoryView provideView(){

        return mView;
    }



    @Provides
    public CategoryContract.ICagegoryModel privodeModel(ApiService apiService){

        return  new CategoryModel(apiService);
    }












}
