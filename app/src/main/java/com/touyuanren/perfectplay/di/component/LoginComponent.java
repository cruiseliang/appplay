package com.touyuanren.perfectplay.di.component;

import com.touyuanren.perfectplay.di.FragmentScope;
import com.touyuanren.perfectplay.di.module.LoginModule;
import com.touyuanren.perfectplay.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by Liang on 2017/10/12 0012.
 */
@FragmentScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);

}
