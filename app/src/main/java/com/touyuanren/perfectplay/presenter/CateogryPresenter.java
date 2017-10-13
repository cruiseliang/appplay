package com.touyuanren.perfectplay.presenter;


import com.touyuanren.perfectplay.bean.Category;
import com.touyuanren.perfectplay.common.rx.RxHttpResponseCompat;
import com.touyuanren.perfectplay.common.rx.Subscriber.ProgressSubcriber;
import com.touyuanren.perfectplay.presenter.contract.CategoryContract;

import java.util.List;

import javax.inject.Inject;


public class CateogryPresenter extends BasePresenter<CategoryContract.ICagegoryModel, CategoryContract.CategoryView> {


    @Inject
    public CateogryPresenter(CategoryContract.ICagegoryModel iCagegoryModel, CategoryContract.CategoryView categoryView) {
        super(categoryView, iCagegoryModel);
    }


    public void getAllCategory() {

        mModel.getCategories().compose(RxHttpResponseCompat.<List<Category>>compatResult())
                .subscribe(new ProgressSubcriber<List<Category>>(context, mView) {
                    @Override
                    public void onNext(List<Category> categories) {

                        mView.showData(categories);
                    }
                });
    }


}
