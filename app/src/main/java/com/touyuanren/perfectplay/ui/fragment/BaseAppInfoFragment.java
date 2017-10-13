package com.touyuanren.perfectplay.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.bean.PageBean;
import com.touyuanren.perfectplay.presenter.AppInfoPresenter;
import com.touyuanren.perfectplay.presenter.contract.AppInfoContract;
import com.touyuanren.perfectplay.ui.adapter.AppInfoAdapter;
import com.touyuanren.perfectplay.ui.decoration.DividerItemDecoration;

import butterknife.BindView;

/**
 * 实现了上拉分页加载；
 */
public abstract class BaseAppInfoFragment extends ProgressFragment<AppInfoPresenter> implements AppInfoContract.AppInfoView,
        BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    protected AppInfoAdapter mAdapter;

    int page = 0;

    @Override
    public void init() {
        mPresenter.requestData(type(), page);

        initRecyclerView();
    }

    protected void initRecyclerView() {


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = buildAdapter();

        mAdapter.setOnLoadMoreListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    abstract int type();

    abstract AppInfoAdapter buildAdapter();

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }


    @Override
    public void showResult(PageBean<AppInfo> pageBean) {

        mAdapter.addData(pageBean.getDatas());
        Log.e("mAdapter", ""+pageBean.getDatas().size());
        if (pageBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(pageBean.isHasMore());
    }

    @Override
    public void onLoadComplete() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {

        mPresenter.requestData(type(), page);
//        mPresenter.getTopList(page);
    }


}
