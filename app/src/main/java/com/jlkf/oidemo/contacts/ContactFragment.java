package com.jlkf.oidemo.contacts;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.contacts.activity.CacheActivity;
import com.jlkf.oidemo.contacts.activity.TakePhoneActivity;
import com.jlkf.oidemo.contacts.adapters.InfoAdapter;
import com.jlkf.oidemo.contacts.provider.ProviderActivity;
import com.jlkf.oidemo.other.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by DuoNuo on 2017/5/22
 */

public class ContactFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.info_rv)
    RecyclerView infoRv;
    private InfoAdapter infoAdapter;

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        return rootView;
    }

    @Override
    protected void initEvents() {
        //入口点击
        infoAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
        //初始化rv
        initInfoRv();
    }

    private void initInfoRv() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        infoRv.setLayoutManager(manager);
        List<String> data = new ArrayList<>();
        data.add("缓存");
        data.add("相机");
        data.add("Provider");
        infoAdapter = new InfoAdapter(R.layout.adapter_info, data);
        infoRv.setAdapter(infoAdapter);
    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position){
            case 0:
                CacheActivity.actionStart(getContext());//缓存
                break;
            case 1:
                TakePhoneActivity.actionStart(getContext());//相机
                break;
            case 2:
                ProviderActivity.actionStart(getContext());//Provider
                break;
        }
    }
}
