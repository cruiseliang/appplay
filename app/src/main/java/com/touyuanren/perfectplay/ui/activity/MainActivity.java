package com.touyuanren.perfectplay.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.touyuanren.perfectplay.R;
import com.touyuanren.perfectplay.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tabLayout_order)
    TabLayout mTabLayoutOrder;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    private View headView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawerLayut();
        initTabLayut();

    }

    private void initDrawerLayut() {
        //        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                Log.e(TAG, "onDrawerSlide");
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                Log.e(TAG, "onDrawerOpened");
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                Log.e(TAG, "onDrawerClosed");
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//                Log.e(TAG, "onDrawerStateChanged");
//            }
//        });
        headView = mNavigationView.getHeaderView(0);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了head", Toast.LENGTH_LONG).show();
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //点击了那个item
                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "点击了更新", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this, "点击了信息", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(MainActivity.this, "点击了设置", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

        mToolBar.inflateMenu(R.menu.menu_toolar);
        //将actionbar与navigation进行绑定
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

    }

    private void initTabLayut() {
        ViewPagerAdapter  viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(viewPagerAdapter);
        mTabLayoutOrder.setupWithViewPager(mViewpager);
    }


}
