package com.leo.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity implements View.OnClickListener{

    private ShortMessageReceiver shortMessageReceiver;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        shortMessageReceiver  = new ShortMessageReceiver();
        Button button = (Button) findViewById(R.id.send);
        button.setOnClickListener(this);
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        NetWorkReceive netWorkReceive = new NetWorkReceive();
        registerReceiver(netWorkReceive, filter);

    }

    private void registerReceiver(){
        registerReceiver(shortMessageReceiver, new IntentFilter("android.provider.TelePhony.SMS_RECEIVED"));
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    private void unRegisterReceiver(){
        unregisterReceiver(shortMessageReceiver);
        Toast.makeText(this,"注销成功",Toast.LENGTH_SHORT).show();
    }

    private void sendBroadcast(){
        Intent intent = new Intent("myBroadcast");
        intent.addCategory("myBroadcast");
        intent.putExtra("test","test....");
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View v) {
        sendBroadcast();
    }

    public class NetWorkReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络打开", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"网络关闭",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
