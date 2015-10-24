package com.leo.socket;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class MyActivity extends Activity
{
    TextView tv;
    Button button;
    Button connect;
    Button close;
    Button send;
    EditText ipEt;
    EditText portEt;
    EditText msgEt;
    TextView statusTv;
    String result = "";
    TcpThread tcpThread;
    String ip;
    int port;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 4){
                statusTv.setText("连接断开");
            }else if(msg.what == 3){
                statusTv.setText("连接失败");
            }else if(msg.what == 1){
                statusTv.setText("连接成功");
            }
            tv.setText(result);
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
        connect = (Button) findViewById(R.id.connect);
        close = (Button) findViewById(R.id.close);
        send = (Button) findViewById(R.id.send);
        ipEt = (EditText) findViewById(R.id.ip);
        portEt = (EditText) findViewById(R.id.port);
        msgEt = (EditText) findViewById(R.id.msg);
        statusTv = (TextView) findViewById(R.id.status);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ip = ipEt.getText().toString();
                    port = Integer.parseInt(portEt.getText().toString());
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MyActivity.this, "ip或端口有误",Toast.LENGTH_SHORT).show();
                }
                tcpThread = new TcpThread(ip, port);
                Thread t = new Thread(tcpThread);
                t.start();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcpThread.close();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcpThread.send(msgEt.getText().toString());
            }
        });
    }
    class TcpThread extends Thread{
        String ip;
        int port;
        Socket socket = new Socket();
        SocketAddress sa;
        OutputStream os = null;
        InputStream is = null;
        boolean status = false;
        public TcpThread(String ip,int port){
            this.ip = ip;
            this.port = port;
        }


        @Override
        public void run() {
            try {
                if (!socket.isConnected()) {
                    try {
                        sa = new InetSocketAddress(ip, port);
                        socket.connect(sa, 500);
                        Log.i("connect", "连接成功");
                        //result += "连接成功\n";
                        handler.sendEmptyMessage(1);
                        status = true;
                    }catch (UnknownHostException e) {
                        handler.sendEmptyMessage(3);
                        //result += "连接失败\n";
                        return;
                    }

                }
                //BufferedReader br = null;
                if(!status) {
                    handler.sendEmptyMessage(4);
                    return;
                }
                new Thread(){
                    @Override
                    public void run() {
                        while (true){
                            try {
                                if(!status) return;
                                try{
                                    socket.sendUrgentData(0xFF);
                                }catch(Exception ex) {
                                    status = false;
                                    handler.sendEmptyMessage(4);
                                    return;
                                }
                                send("1");
                                result += "发送心跳"+ System.currentTimeMillis()+"\n";
                                handler.sendEmptyMessage(1);

                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                handler.sendEmptyMessage(4);
                            }
                        }
                    }
                }.start();

                try {
                    is = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int i = -1;
                    while ((i = is.read(bytes)) != -1) {
                        result +="接收:"+new String(bytes, "utf-8")+"\n";
                        Log.i("receive", result);
                        handler.sendEmptyMessage(1);
                    }

                    //br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //final BufferedReader finalBr = br;
                    String line = "";
/*                    while (true){
                        *//*try {
                            while ((line = finalBr.readLine())!=null) {
                                result+= line;
                            }
                            Log.i("receive", result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*//*
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String msg) {
            byte[] bytes = msg.getBytes();
            try {
                os = socket.getOutputStream();
                os.write(bytes);
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void close(){
                try {
                    if (is != null) {
                        is.close();
                        is = null;
                    }
                    if(os != null){
                        os.close();
                        os = null;
                    }
                    if (socket != null && socket.isConnected()) {
                        socket.close();
                    }
                    socket = null;
                    status = false;
                    handler.sendEmptyMessage(4);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
}
