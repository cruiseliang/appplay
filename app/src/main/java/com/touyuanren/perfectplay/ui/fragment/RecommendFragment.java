package com.touyuanren.perfectplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerRecommendComponent;
import com.touyuanren.perfectplay.di.module.RecommendModule;
import com.touyuanren.perfectplay.presenter.RecommendPresenter;
import com.touyuanren.perfectplay.presenter.contract.RecommendContract;
import com.touyuanren.perfectplay.ui.adapter.RecommendAppAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private RecommendAppAdapter adapter;
    @Inject
     ProgressDialog mProgressDialog;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().recommendModule(new RecommendModule(this)).appComponent(appComponent).build().inject(this);
    }

    @Override
    public void init() {
        mPresenter.requestDatas();
    }


    private void initRecyclerView(List<AppInfo> datas) {
        adapter = new RecommendAppAdapter(getActivity(), datas);
        //为RecyclerView设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), com.touyuanren.perfectplay.ui.decoration.DividerItemDecoration.HORIZONTAL_LIST));

        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecyclerView(datas);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器开小差了：" + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
