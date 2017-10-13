package com.touyuanren.perfectplay.presenter.contract;


import com.touyuanren.perfectplay.bean.BaseBean;
import com.touyuanren.perfectplay.bean.Category;
import com.touyuanren.perfectplay.ui.BaseView;

import java.util.List;

import rx.Observable;



public interface CategoryContract {


    public interface  ICagegoryModel{


         Observable<BaseBean<List<Category>>> getCategories();

    }


    public interface  CategoryView extends BaseView {



        public void showData(List<Category> categories);


    }
}
