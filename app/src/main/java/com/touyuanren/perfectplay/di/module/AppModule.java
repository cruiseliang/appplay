package com.touyuanren.perfectplay.di.module;

import com.google.gson.Gson;
import com.touyuanren.perfectplay.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2017/10/10 0010.
 */
@Module
public class AppModule {
    private AppApplication application;

    public AppModule(AppApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public AppApplication provideApplication() {
        return application;

    }
    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();

    }

}
