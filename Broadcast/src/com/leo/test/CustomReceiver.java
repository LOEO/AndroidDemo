package com.leo.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by LT on 2015-05-24.
 */
public class CustomReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if("myBroadcast".equals(intent.getAction())){
            String str = intent.getStringExtra("test");
            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
        }
    }
}
