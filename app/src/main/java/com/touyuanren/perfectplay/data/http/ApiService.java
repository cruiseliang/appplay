package com.touyuanren.perfectplay.data.http;


import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.PageBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 2016/12/30.
 */

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";




//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured")
    public Observable<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

}
