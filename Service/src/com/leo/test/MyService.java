package com.leo.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by LT on 2015-05-24.
 */
public class MyService extends Service {
    String tag = "tag";
    MyBinder myBinder = new MyBinder();
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
        Toast.makeText(this,"OnDestroy",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(tag, "onStartCommand");
        Toast.makeText(this,"onStartCommand",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show();
        super.onStart(intent, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag, "onCreate");
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"onBind",Toast.LENGTH_SHORT).show();
        return myBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this,"onRebind",Toast.LENGTH_SHORT).show();
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"onUnbind",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }
}
