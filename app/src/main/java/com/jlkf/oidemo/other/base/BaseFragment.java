package com.jlkf.oidemo.other.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.event.OnTitleEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description
 *
 * @author created by wuwang on 2016/4/25
 */
public abstract class BaseFragment extends Fragment implements OnTitleEvent {

    public View rootView;
    protected Title title;
    protected Context mContext;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContext = getActivity().getApplicationContext();
        if (rootView == null) {
            rootView = onCreateRootView(inflater, container);
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        init();
        return rootView;
    }

    public void init(){
        unbinder = ButterKnife.bind(this, rootView);
        initViews();
        initDatas();
        initEvents();
    }

    protected abstract void initEvents();
    protected abstract void initDatas();
    protected abstract void initViews();

    public abstract View onCreateRootView(LayoutInflater inflater, ViewGroup container);


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /******************************标题栏********************************************************/
    
    protected void supportTitle(boolean isSupport){
        if(isSupport){
            View v = rootView.findViewById(R.id.title);
            if(v!=null){
                title = new Title(v,this);
            }else{
                try {
                    throw new Exception("Cannot find Title");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCenterClick(View view) {

    }

    @Override
    public void onLeftClick(View view) {

    }

    @Override
    public void onRightClick(View view) {

    }

    /**************************公共方法************************************************************/

    public int gColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getActivity().getColor(id);
        } else {
            return this.getResources().getColor(id);
        }
    }

    protected void hideSoftKeyboard() {
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public Toast toast(CharSequence toast) {
        Toast t = Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT);
        t.show();
        return t;
    }


    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

}
