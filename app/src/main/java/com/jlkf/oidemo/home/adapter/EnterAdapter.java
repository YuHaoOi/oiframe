package com.jlkf.oidemo.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.oidemo.AppConstants;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.home.bean.EnterBean;
import com.jlkf.oidemo.other.common.RecyclerAdapter;
import com.jlkf.oidemo.other.common.RecyclerHolder;
import com.jlkf.oidemo.other.utils.ClickUtils;
import com.jlkf.oidemo.other.utils.OiGlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DuoNuo on 2017/5/22
 */

public class EnterAdapter extends RecyclerAdapter<EnterBean, EnterAdapter.EnterHolder> {

    public EnterAdapter(Context context, List<EnterBean> data) {
        super(context, data);
    }

    @Override
    public EnterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EnterHolder(createView(R.layout.adapter_enter, parent));
    }

    @Override
    public void onBindViewHolder(EnterHolder holder, int position) {
        EnterBean enterBean = data.get(position);
        OiGlideUtils.loadImageView(context, enterBean.resId, holder.enterIv);
        holder.enterTv.setText(enterBean.title);
        ClickUtils.setPos(holder.itemView, position);
    }

    class EnterHolder extends RecyclerHolder {
        @BindView(R.id.enter_iv)
        ImageView enterIv;
        @BindView(R.id.enter_tv)
        TextView enterTv;

        public EnterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ClickUtils.addClickTo(itemView, getOnClickListener(), AppConstants.CLICK_ITEM);
        }
    }
}
