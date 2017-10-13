package com.touyuanren.perfectplay.data;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.BaseBean;
import com.touyuanren.perfectplay.bean.IndexBean;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.data.http.ApiService;

import rx.Observable;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class AppInfoModel {

    private ApiService mApiService;

    public AppInfoModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getApps() {


//        ABHttpManager manager = new ABHttpManager();
//
//        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
//        mApiService.getApps("{'page':0}").enqueue(callback);
        return mApiService.getApps("{'page':0}");

    }

    public Observable<BaseBean<IndexBean>> index() {


//        ABHttpManager manager = new ABHttpManager();
//
//        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
//        mApiService.getApps("{'page':0}").enqueue(callback);
        return mApiService.index();

    }

    //    public void getApps(Callback<PageBean<AppInfo>> callback){
//
//       mApiService.getApps("{'page':0}").enqueue(callback);
//
//
//    }
    public Observable<BaseBean<PageBean<AppInfo>>> topList(int page) {
        return mApiService.topList(page);
    }
    public Observable<BaseBean<PageBean<AppInfo>>> games(int page) {
        return mApiService.games(page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory( int categoryid,  int page){

        return  mApiService.getFeaturedAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory( int categoryid, int page){

        return  mApiService.getTopListAppsByCategory(categoryid,page);
    }

    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory( int categoryid, int page){

        return  mApiService.getNewListAppsByCategory(categoryid,page);
    }

}
