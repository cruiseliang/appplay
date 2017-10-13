package com.touyuanren.perfectplay.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.Category;
import com.touyuanren.perfectplay.common.Constant;
import com.touyuanren.perfectplay.di.component.AppComponent;
import com.touyuanren.perfectplay.di.component.DaggerCategoryComponent;
import com.touyuanren.perfectplay.di.module.CategoryModule;
import com.touyuanren.perfectplay.presenter.CateogryPresenter;
import com.touyuanren.perfectplay.presenter.contract.CategoryContract;
import com.touyuanren.perfectplay.ui.activity.CategoryAppActivity;
import com.touyuanren.perfectplay.ui.adapter.CategoryAdapter;
import com.touyuanren.perfectplay.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;


/**
 * Created by Ivan on 16/9/26.
 */

public class CategoryFragment extends ProgressFragment<CateogryPresenter>  implements CategoryContract.CategoryView{


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private CategoryAdapter mAdapter;
    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    @Override
    public int setLayout() {
        return R.layout.template_recyclerview;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder().appComponent(appComponent).categoryModule(new CategoryModule(this))
                .build().inject(this);
    }

    @Override
    public void showData(List<Category> categories) {
        mAdapter.addData(categories);
    }

    private void initRecyclerView(){


        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()) );

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);

        mRecycleView.addItemDecoration(itemDecoration);
        mAdapter = new CategoryAdapter();

        mRecycleView.setAdapter(mAdapter);

        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);

                intent.putExtra(Constant.CATEGORY,mAdapter.getData().get(position));

                startActivity(intent);
            }
        });

    }
}
