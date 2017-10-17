package com.jlkf.oidemo.contacts.brocastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jlkf.oidemo.AppConstants;

/**
 * Created by DuoNuo on 2017/10/17
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(AppConstants.INTENT_MSG);
        Toast.makeText(context, "接收到的信息：" + msg, Toast.LENGTH_SHORT).show();
        //放弃当前的广播，则优先级低的无法收到当前广播
        //abortBroadcast();
        //优先级高的广播接收者想给优先级低的广播接收者传递数据可以通过下面的方法
        //setResultData()
        //setResultCode()
        //setResultExtra()
        //setResult()----------（相当于上面三个方法）
        setResultData("这是前一个广播发送的信息");

    }
}
