package com.touyuanren.perfectplay.ui.fragment;

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

import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.data.http.ABHttpManager;
import com.touyuanren.perfectplay.data.http.ApiService;
import com.touyuanren.perfectplay.ui.adapter.RecommendAppAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Liang on 2017/10/9 0009.
 * mcv模式下的首页推荐列表frag；项目主要是采用mvp
 */

public class RecommendFragmentMvc extends Fragment {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    private RecommendAppAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.template_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        ABHttpManager manager = new ABHttpManager();
        ApiService service = manager.getRetrofit(manager.getOkHttpClient()).create(ApiService.class);
//        service.getApps("{'page':0}").enqueue(new Callback<PageBean<AppInfo>>() {
//            @Override
//            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
//                PageBean<AppInfo> info = response.body();
//                //进行赋值
//                List<AppInfo> datas = info.getDatas();
//                initRecyclerView(datas);
//            }
//
//            @Override
//            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRecyclerView(List<AppInfo> datas) {
        adapter = new RecommendAppAdapter(getActivity(), datas);
        //为RecyclerView设置布局管理器
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(adapter);
    }
}
