package com.touyuanren.perfectplay;

import android.app.Application;
import android.content.Context;

import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerAppComponent;
import com.touyuanren.perfectplay.di.module.AppModule;
import com.touyuanren.perfectplay.di.module.HttpModule;

/**
 * Created by Liang on 2017/10/10 0010.
 */

public class AppApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).httpModule(new HttpModule()).build();
//         DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }
}
