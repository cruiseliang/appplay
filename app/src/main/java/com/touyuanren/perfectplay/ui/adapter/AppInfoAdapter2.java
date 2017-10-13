package com.touyuanren.perfectplay.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.AppInfo;
import com.touyuanren.perfectplay.common.imageloader.ImageLoader;

/**
 * Created by Liang on 2017/10/12 0012.
 */

public class AppInfoAdapter2 extends BaseQuickAdapter<AppInfo, BaseViewHolder> {
    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public AppInfoAdapter2() {
        super(R.layout.template_appinfo);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName()).setText(R.id.txt_brief, item.getBriefShow()).setText(R.id.txt_position,item.getPosition());

    }
}
