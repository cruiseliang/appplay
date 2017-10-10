package com.touyuanren.perfectplay.di.component;

import com.touyuanren.perfectplay.di.FragmentScope;
import com.touyuanren.perfectplay.di.module.RecommendModule;
import com.touyuanren.perfectplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by Liang on 2017/10/10 0010.
 */
@FragmentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {
    void inject(RecommendFragment fragment);
}
