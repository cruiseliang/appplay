package com.touyuanren.perfectplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.bean.AppInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class RecommendAppAdapter extends RecyclerView.Adapter<RecommendAppAdapter.ViewHolder> {
    private Context mContext;
    private List<AppInfo> listData;
    private LayoutInflater inflater;

    public RecommendAppAdapter(Context context, List<AppInfo> listData) {
        this.listData = listData;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.template_recomend_app, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    //赋值
        AppInfo appInfo = listData.get(position);

//        holder.mImgIcon

        String baseImgUrl ="http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
        Picasso.with(mContext).load(baseImgUrl +appInfo.getIcon()).into(holder.imgIcon);

        holder.textTitle.setText(appInfo.getDisplayName());
        holder.textSize.setText((appInfo.getApkSize() / 1024 /1024) +" MB");
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_size)
        TextView textSize;
        @BindView(R.id.btn_dl)
        Button btnDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
