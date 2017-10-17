package com.jlkf.oidemo.contacts.brocastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.jlkf.oidemo.AppConstants;

/**
 * Created by DuoNuo on 2017/10/17
 */

public class MySecondReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra(AppConstants.INTENT_MSG);
        //优先级低的可以通过下面方法得到数据：(注意：前提不能拦截广播)
        //getResultData()
        //getResultCode()
        //getResultExtra()
        Toast.makeText(context, "接收到的信息：" + msg + "\n" + getResultData(), Toast.LENGTH_SHORT).show();
    }
}
