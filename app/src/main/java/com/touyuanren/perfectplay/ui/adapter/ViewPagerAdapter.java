package com.touyuanren.perfectplay.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.touyuanren.perfectplay.bean.FragmentInfo;
import com.touyuanren.perfectplay.ui.fragment.CategoryFragment;
import com.touyuanren.perfectplay.ui.fragment.GamesFragment;
import com.touyuanren.perfectplay.ui.fragment.TopListFragment;
import com.touyuanren.perfectplay.ui.fragment.RecommendFragment;

import java.util.ArrayList;

/**
 * Created by Liang on 2017/9/30 0030.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles = new String[]{ "推荐","排行", "游戏","分类"};
    private ArrayList<FragmentInfo> mFragments = new ArrayList<>(4);

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();

    }

    private void initFragment() {
        mFragments.add(new FragmentInfo( "推荐", RecommendFragment.class));
        mFragments.add(new FragmentInfo( "排行", TopListFragment.class));
        mFragments.add(new FragmentInfo( "游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo( "分类", CategoryFragment.class));



    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragments.get(position).getmFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
