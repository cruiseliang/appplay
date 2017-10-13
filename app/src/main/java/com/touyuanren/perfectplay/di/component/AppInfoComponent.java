package com.touyuanren.perfectplay.di.component;

import com.touyuanren.perfectplay.di.FragmentScope;
import com.touyuanren.perfectplay.di.module.AppInfoModule;
import com.touyuanren.perfectplay.ui.fragment.CategoryAppFragment;
import com.touyuanren.perfectplay.ui.fragment.GamesFragment;
import com.touyuanren.perfectplay.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Created by Liang on 2017/10/12 0012.
 */
@FragmentScope
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {
    void inject(TopListFragment fragment);
    void injectGamesFragment(GamesFragment fragment);
    void injectCategoryAppFragment(CategoryAppFragment fragment);
}
