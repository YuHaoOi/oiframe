package com.jlkf.oidemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jlkf.oidemo.contacts.ContactFragment;
import com.jlkf.oidemo.home.HomeFragment;
import com.jlkf.oidemo.other.adapter.MyPagerAdapter;
import com.jlkf.oidemo.other.base.BaseActivity;
import com.jlkf.oidemo.other.utils.StatusBarUtil;
import com.jlkf.oidemo.other.utils.TabEntity;
import com.jlkf.oidemo.personal.PersonalFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.tablayout_home)
    CommonTabLayout mTablayoutHome;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"课程", "通讯录", "个人中心"};
    private int[] mIconUnselectIds = {
            R.mipmap.home_tab1, R.mipmap.home_tab2,
            R.mipmap.home_tab3};
    private int[] mIconSelectIds = {
            R.mipmap.home_tab11, R.mipmap.home_tab22,
            R.mipmap.home_tab33};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int lastSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        //透明状态栏
        StatusBarUtil.transparencyBar(this);
        //状态栏字体加深
        StatusBarUtil.StatusBarDarkMode(this);
        //初始化标签
        initTabs();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    private void initTabs() {
        mFragments.add(new HomeFragment());
        mFragments.add(new ContactFragment());
        mFragments.add(new PersonalFragment());

        mVpHome.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mVpHome.setOffscreenPageLimit(2);
        mTablayoutHome.setTabData(mTabEntities);
        mTablayoutHome.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelect(int position) {
                mVpHome.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastSel = position;
                mTablayoutHome.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
