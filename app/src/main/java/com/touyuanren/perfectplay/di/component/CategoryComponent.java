package com.touyuanren.perfectplay.di.component;


import com.touyuanren.perfectplay.di.FragmentScope;
import com.touyuanren.perfectplay.di.module.CategoryModule;
import com.touyuanren.perfectplay.ui.fragment.CategoryFragment;

import dagger.Component;


@FragmentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {


    void inject(CategoryFragment fragment);
}
