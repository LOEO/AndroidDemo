package com.leo.AndroidThreadTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity implements View.OnClickListener{

    private static final int UPDATE_TEXT = 1;

    Button btn;
    TextView tv;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    tv.setText("ssdfsdf");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.text);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            }).start();
        }

    }
}
