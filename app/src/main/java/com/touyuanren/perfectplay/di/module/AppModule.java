package com.touyuanren.perfectplay.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liang on 2017/10/10 0010.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;

    }
    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();

    }

}
