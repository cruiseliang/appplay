package com.touyuanren.perfectplay.di.component;

import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.di.module.AppModule;
import com.touyuanren.perfectplay.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Liang on 2017/10/10 0010.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    public ApiService getApiService();
}
