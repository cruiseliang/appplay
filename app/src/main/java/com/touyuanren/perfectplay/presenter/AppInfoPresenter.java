package com.touyuanren.perfectplay.presenter;

import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.BaseBean;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.common.rx.RxHttpResponseCompat;
import com.touyuanren.perfectplay.common.rx.Subscriber.ErrorHandleSubscriber;
import com.touyuanren.perfectplay.common.rx.Subscriber.ProgressSubcriber;
import com.touyuanren.perfectplay.data.AppInfoModel;
import com.touyuanren.perfectplay.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Liang on 2017/10/12 0012.
 */

public class AppInfoPresenter extends BasePresenter<AppInfoModel, AppInfoContract.AppInfoView> {
    public static final int  TOP_LIST=1;
    public static final int  GAME=2;
    public static final int  CATEGORY=3;


    public static final int FEATURED=0;
    public static final int TOPLIST=1;
    public static final int NEWLIST=2;
    @Inject
    public AppInfoPresenter(AppInfoContract.AppInfoView topListView, AppInfoModel appInfoModel) {
        super(topListView, appInfoModel);
    }

    public void requestData(int type,int page) {
        request(type,page,0,0);
    }
    public void  request(int type,int page,int categoryId,int flagType){


        Subscriber subscriber =null;

        if(page==0){

            // 第一页显示loading -----
            subscriber = new  ProgressSubcriber<PageBean<AppInfo>>(context,mView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        }
        else {

            // 加载下一页
            subscriber = new ErrorHandleSubscriber<PageBean<AppInfo>>(context) {
                @Override
                public void onCompleted() {
                    mView.onLoadComplete();
                }

                @Override
                public void onNext(PageBean<AppInfo> pageBean) {
                    mView.showResult(pageBean);
                }
            };
        }
        Observable observable = getObservable(type,page,categoryId,flagType);

        observable
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(subscriber);


    }


    public void requestCategoryApps(int categoryId,int page,int flagType){

        request(CATEGORY,page,categoryId,flagType);


    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type,int page,int categoryId,int flagType){

        switch (type){

            case TOP_LIST:
                return  mModel.topList(page);


            case GAME:
                return  mModel.games(page);

            case CATEGORY:

                if(flagType==FEATURED){

                    return  mModel.getFeaturedAppsByCategory(categoryId,page);
                }

                else  if(flagType==TOPLIST){

                    return  mModel.getTopListAppsByCategory(categoryId,page);
                }

                else  if(flagType==NEWLIST){

                    return  mModel.getNewListAppsByCategory(categoryId,page);
                }

            default:
                return Observable.empty();
        }
    }

}
