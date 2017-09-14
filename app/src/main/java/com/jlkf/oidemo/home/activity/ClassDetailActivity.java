package com.jlkf.oidemo.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.contacts.ContactFragment;
import com.jlkf.oidemo.other.base.BaseActivity;
import com.jlkf.oidemo.other.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassDetailActivity extends BaseActivity {

    @BindView(R.id.cover_iv)
    ImageView coverIv;
    @BindView(R.id.taylayout)
    SlidingTabLayout taylayout;
    @BindView(R.id.class_vp)
    ViewPager classVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(ClassDetailActivity.this);
        supportTitle(true);
        title.setTitle("课程详情", "编辑");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ContactFragment());
        fragments.add(new ContactFragment());
        fragments.add(new ContactFragment());
        taylayout.setViewPager(classVp, new String[]{"课程介绍", "学员", "评价"}, this, fragments);
    }

    @Override
    protected void initEvents() {
    }

    @Override
    protected void initDatas() {
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ClassDetailActivity.class);
        context.startActivity(intent);
    }
}
