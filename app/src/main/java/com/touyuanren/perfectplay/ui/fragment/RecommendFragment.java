package com.touyuanren.perfectplay.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.presenter.RecommendPresenter;
import com.touyuanren.perfectplay.presenter.contract.RecommendContract;
import com.touyuanren.perfectplay.ui.adapter.RecomendAppAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends Fragment implements RecommendContract.View {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    private RecomendAppAdapter adapter;
    private ProgressDialog mProgressDialog;

    private RecommendContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        unbinder = ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(getActivity());

        mPresenter = new RecommendPresenter(this);
        initData();
        return view;
    }

    private void initData() {
        mPresenter.requestDatas();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRecyclerView(List<AppInfo> datas) {
        adapter = new RecomendAppAdapter(getActivity(), datas);
        //为RecyclerView设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

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
