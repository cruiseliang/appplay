package com.touyuanren.perfectplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.IndexBean;
import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerRecommendComponent;
import com.touyuanren.perfectplay.di.module.RecommendModule;
import com.touyuanren.perfectplay.presenter.RecommendPresenter;
import com.touyuanren.perfectplay.presenter.contract.AppInfoContract;
import com.touyuanren.perfectplay.ui.adapter.IndexMultiAdapter;

import butterknife.BindView;


/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private IndexMultiAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }

    @Override
    public int setLayout() {
        return R.layout.template_recyclerview;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().recommendModule(new RecommendModule(this)).appComponent(appComponent).build().inject(this);
    }

    @Override
    public void init() {
        mPresenter.requsetPermission();
        initRecyclerView();
    }

    private void initRecyclerView() {

        //为RecyclerView设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), com.touyuanren.perfectplay.ui.decoration.DividerItemDecoration.HORIZONTAL_LIST));
        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void showResult(IndexBean mIndexBean) {

        adapter = new IndexMultiAdapter(getActivity());
        adapter.setData(mIndexBean);
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器开小差了：" + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionSError() {
        //没有权限
        Toast.makeText(getActivity(), "你已拒绝授权", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionSuccess() {
        mPresenter.requestDatas();
    }
}
