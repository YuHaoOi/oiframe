package com.jlkf.oidemo.contacts.brocastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jlkf.oidemo.AppConstants;

/**
 * Created by DuoNuo on 2017/10/17.
 */

public class MyLocalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(AppConstants.INTENT_MSG);
        Toast.makeText(context, "接收到的信息：" + msg, Toast.LENGTH_SHORT).show();
    }
}
