package com.jlkf.oidemo.contacts.brocastreceiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.jlkf.oidemo.AppConstants;
import com.jlkf.oidemo.R;
import com.jlkf.oidemo.other.base.BaseActivity;

public class BrocastReceiverActivity extends BaseActivity {

    private MySecondReceiver receiver;
    private MyLocalBroadcastReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brocast_receiver);
    }

    @Override
    protected void initViews() {
        registerDynamicBrocast();
        registerLocalBrocast();
    }

    //动态注册广播
    //动态注册的广播接受者只能在解除注册之前收到广播，静态注册的广播只要注册他的app存在就可以收到广播
    private void registerDynamicBrocast() {
        receiver = new MySecondReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BrocastContacts.BROCAST_ACTION);
        intentFilter.setPriority(50); //测试有序广播
        registerReceiver(receiver, intentFilter);
    }

    //注册本地广播
    /*首先我们要知道，普通的广播每当应用程序发送一个广播时系统会将发送的Intent与系统中所有注册的BroadcastReceiver
    的IntentFilter进行匹配，若匹配成功则执行相应的onReceive函数。如果该广播只是用在应用内通讯，则这样效率较低。
    LocalBroadcastManager相对 BroadcastReceiver，它只能用于应用内通信，安全性更好，同时拥有更高的运行效率。
    也是需发送应用内广播时的官方推荐*/
    private void registerLocalBrocast() {
        localReceiver = new MyLocalBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(localReceiver, new IntentFilter(BrocastContacts.LOCAL_BROCAST_ACTION));
    }


    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BrocastReceiverActivity.class);
        context.startActivity(intent);
    }

    //发送广播
    public void sendBroadcast(View view) {
        Intent intent = new Intent(BrocastContacts.BROCAST_ACTION);
        intent.putExtra(AppConstants.INTENT_MSG, "hello world");
        sendBroadcast(intent);
    }

    //发送有序广播
    public void sendDynamicBroadcast(View view) {
        Intent intent = new Intent(BrocastContacts.BROCAST_ACTION);
        intent.putExtra(AppConstants.INTENT_MSG, "hello world");
        //参数二是接收器的权限，不需要权限填null
        sendOrderedBroadcast(intent, null);
    }

    //发送权限广播
    public void sendPermissonBroadcast(View view) {
        Intent intent = new Intent(BrocastContacts.BROCAST_ACTION);
        intent.putExtra(AppConstants.INTENT_MSG, "hello world");
        sendBroadcast(intent, "com.yuhao.BROCAST");
    }

    //发送本地广播
    public void sendQuickBroadcast(View view) {
        Intent intent = new Intent(BrocastContacts.LOCAL_BROCAST_ACTION);
        intent.putExtra(AppConstants.INTENT_MSG, "hello world");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册--内存溢出
        unregisterReceiver(receiver);
        //解除注册本地广播
        if (localReceiver != null){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(localReceiver);
        }
    }
}
