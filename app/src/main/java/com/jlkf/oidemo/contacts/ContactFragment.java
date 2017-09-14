package com.jlkf.oidemo.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseFragment;

/**
 * Created by DuoNuo on 2017/5/22
 */

public class ContactFragment extends BaseFragment {
    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        return rootView;
    }
}
