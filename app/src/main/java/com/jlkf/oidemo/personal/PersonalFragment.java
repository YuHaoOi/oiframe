package com.jlkf.oidemo.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseFragment;


/**
 * Created by DuoNuo on 2017/5/22
 */

public class PersonalFragment extends BaseFragment {

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        return rootView;
    }
}
