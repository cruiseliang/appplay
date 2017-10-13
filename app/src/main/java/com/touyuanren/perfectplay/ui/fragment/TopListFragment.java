package com.touyuanren.perfectplay.ui.fragment;

import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerAppInfoComponent;
import com.touyuanren.perfectplay.di.module.AppInfoModule;
import com.touyuanren.perfectplay.presenter.AppInfoPresenter;
import com.touyuanren.perfectplay.ui.adapter.AppInfoAdapter;


/**
 * Created by Ivan on 16/9/26.
 */

public class TopListFragment extends BaseAppInfoFragment {

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder().appComponent(appComponent).appInfoModule(new AppInfoModule(this)).build().inject(this);

    }

    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder().showPosition(true).showBrief(false).showCategoryName(true).build();
    }

}
