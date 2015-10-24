package com.leo.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by LT on 2015-05-22.
 */
public class ShortMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Set<String> keys = bundle.keySet();
            Object[] objects = (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessages = new SmsMessage[objects.length];
            for(int i=0;i<objects.length;i++){
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
                String s = "手机号" + smsMessages[i].getOriginatingAddress() + "\n";
                s += "短信内容:" + smsMessages[i].getDisplayMessageBody();
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
