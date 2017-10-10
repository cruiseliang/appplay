package com.touyuanren.perfectplay.data;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.data.http.ApiService;

import rx.Observable;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class RecommendModel  {

    private ApiService  mApiService;

    public RecommendModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public Observable<PageBean<AppInfo>> getApps(){



//        ABHttpManager manager = new ABHttpManager();
//
//        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
//        mApiService.getApps("{'page':0}").enqueue(callback);
     return   mApiService.getApps("{'page':0}");

    }


//    public void getApps(Callback<PageBean<AppInfo>> callback){
//
//       mApiService.getApps("{'page':0}").enqueue(callback);
//
//
//    }
}
