package com.touyuanren.perfectplay.data;

import com.touyuanren.perfectplay.bean.BaseBean;
import com.touyuanren.perfectplay.bean.Category;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.presenter.contract.CategoryContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Liang on 2017/10/13 0013.
 */

public class CategoryModel implements  CategoryContract.ICagegoryModel{
    private ApiService mApiService;

    public CategoryModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }
    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {

        return mApiService.getCategories();
    }
}
