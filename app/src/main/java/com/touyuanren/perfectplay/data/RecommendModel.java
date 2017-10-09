package com.touyuanren.perfectplay.data;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.http.ApiService;
import com.touyuanren.perfectplay.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class RecommendModel  {

    public  void getApps(Callback<PageBean<AppInfo>> callback){



        HttpManager manager = new HttpManager();

        ApiService apiService =manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);


        apiService.getApps("{'page':0}").enqueue(callback);

    }

}
