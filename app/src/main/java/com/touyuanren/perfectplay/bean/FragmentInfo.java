package com.touyuanren.perfectplay.bean;

/**
 * Created by Liang on 2017/9/30 0030.
 */

public class FragmentInfo {
    private String title;
    private Class  mFragment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getmFragment() {
        return mFragment;
    }

    public void setmFragment(Class mFragment) {
        this.mFragment = mFragment;
    }

    public FragmentInfo(String title, Class mFragment) {
        this.title = title;
        this.mFragment = mFragment;
    }
}
