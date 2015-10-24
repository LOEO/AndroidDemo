package com.leo.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by LT on 2015/8/20 0020.
 */
public class TestHandlerActivity extends Activity implements Handler.Callback {

    private Handler mainHandler;
    private Handler handler;
    private TextView tv;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        tv = (TextView) findViewById(R.id.tv);
        mainHandler = new Handler(getMainLooper(),this);
/*        HandlerThread ht = new HandlerThread("TEST");
        ht.start();
        handler = new Handler(ht.getLooper(), this);*/
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        mainHandler.sendEmptyMessage(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        tv.setText(msg.what+"");
        msg.recycle();
        return false;
    }

    @Override
    protected void onDestroy() {
        if (thread.isAlive()) {
            thread.interrupt();
        }
        super.onDestroy();
    }
}
