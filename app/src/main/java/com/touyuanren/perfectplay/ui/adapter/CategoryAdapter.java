package com.touyuanren.perfectplay.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.Category;
import com.touyuanren.perfectplay.common.Constant;
import com.touyuanren.perfectplay.common.imageloader.ImageLoader;


public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {





    public CategoryAdapter() {

        super(R.layout.template_category);

    }




    @Override
    protected void convert(BaseViewHolder helper, Category item) {

        helper.setText(R.id.text_name,item.getName());

        ImageLoader.load(Constant.BASE_IMG_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }





}
