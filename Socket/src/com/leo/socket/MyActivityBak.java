package com.leo.socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivityBak extends Activity implements View.OnClickListener
{
    TextView tv;
    Button button;
    Button connect;
    Button close;
    Button send;
    Client client;
    String result = "";

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                tv.setText(result);
            }
        }
    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.text);
        button.setOnClickListener(this);
        connect = (Button) findViewById(R.id.connect);
        close = (Button) findViewById(R.id.close);
        send = (Button) findViewById(R.id.send);
        client = new Client("192.168.1.113", 4001);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(client.isConnected()) return;
                Thread t = new Thread(new TcpThread());
                t.start();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.close();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        client.sendMsg("123");
                        Log.i("send","123123");
                    }
                }.start();

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    class TcpThread extends Thread{
        Thread t = null;
        @Override
        public void run() {
            if(!client.isConnected()){
                if(client.conn()){
                    result += "连接成功";
                    handler.sendEmptyMessage(1);
                }
            }
            t = new Thread(){
                @Override
                public void run() {
                    String s = null;
                    while(true){
                         s = client.recMsg();
                        if((s = client.recMsg()) != null){
                            Log.i("receive",result);
                            result += s;
                            handler.sendEmptyMessage(1);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };
            t.start();
        }
    }

}
