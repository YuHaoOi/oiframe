package com.jlkf.oidemo.home;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.oidemo.AppConstants;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.home.activity.ClassDetailActivity;
import com.jlkf.oidemo.home.adapter.EnterAdapter;
import com.jlkf.oidemo.home.bean.EnterBean;
import com.jlkf.oidemo.other.base.BaseFragment;
import com.jlkf.oidemo.other.utils.ClickUtils;
import com.jlkf.oidemo.other.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DuoNuo on 2017/5/22
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.enter_rv)
    RecyclerView enterRv;
    private Unbinder unbinder;
    private EnterAdapter enterAdapter;

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initOiViews();
        initOiEvents();
        return rootView;
    }

    protected void initOiEvents() {
        enterAdapter.setOnClickListener(new ClickUtils.OnClickListener() {
            @Override
            public void onClick(View v, int type, int pos, int child) {
                switch (type){
                    case AppConstants.CLICK_ITEM:
                        ClassDetailActivity.actionStart(getContext());
                        break;
                }
            }
        });
    }

    protected void initOiViews() {
        initBanner();
        initEnterRv();
    }

    private void initEnterRv() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        enterRv.setLayoutManager(gridLayoutManager);
        List<EnterBean> enterData = new ArrayList<>();
        enterData.add(new EnterBean("我要开课", R.mipmap.enter_1));
        enterData.add(new EnterBean("通话记录", R.mipmap.enter_2));
        enterData.add(new EnterBean("我要推荐", R.mipmap.enter_3));
        enterData.add(new EnterBean("我要反馈", R.mipmap.enter_4));
        enterAdapter = new EnterAdapter(getContext(), enterData);
        enterRv.setAdapter(enterAdapter);
    }

    private void initBanner() {
        List<Integer> images = new ArrayList<>();
        Collections.addAll(images, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
