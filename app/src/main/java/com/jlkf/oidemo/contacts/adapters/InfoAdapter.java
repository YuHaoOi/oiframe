package com.jlkf.oidemo.contacts.adapters;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jlkf.oidemo.R;

import java.util.List;

/**
 * Created by DuoNuo on 2017/10/14.
 */

public class InfoAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

    public InfoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.enter_tv, item);
    }
}
