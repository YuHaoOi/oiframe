package com.jlkf.oidemo.other.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/***
 * * @description
 * * 由 马君 创建于 2016年02月19日 20:10
 ***/
public abstract class RecyclerAdapter<B,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public Context context;
    public List<B> data;
    private View.OnClickListener listener;

    private int tag=0;

    public RecyclerAdapter(Context context, List<B> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public View createView(int id, ViewGroup parent){
        if(context==null){
            try {
                throw new Exception("Context cannot be null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return LayoutInflater.from(context).inflate(id,parent,false);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public View.OnClickListener getOnClickListener(){
        return this.listener;
    }

    public void setTag(int tag){
        this.tag=tag;
    }

    public int getTag(){
        return this.tag;
    }

}
