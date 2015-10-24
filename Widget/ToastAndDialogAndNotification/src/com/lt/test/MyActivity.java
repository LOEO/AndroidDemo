package com.lt.test;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener{
    private Button open1;
    private Button open2;
    private Button notifyBtn;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    void initView(){
        open1 = (Button) findViewById(R.id.open1);
        open2 = (Button) findViewById(R.id.open2);
        notifyBtn = (Button) findViewById(R.id.notify);
        open1.setOnClickListener(this);
        open2.setOnClickListener(this);
        notifyBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open1:
                new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle("确认？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(MyActivity.this).setMessage("点击确定").create().show();
                }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AlertDialog.Builder(MyActivity.this).setMessage("点击取消").create().show();
                    }
                }).show();
                break;
            case R.id.open2:
                final String [] items = {
                        "测试1",
                        "测试2",
                        "测试3",
                        "测试4",
                        "测试5",
                        "测试6",
                        "测试7",
                        "测试8",
                        "测试9",
                        "测试10",
                        "测试11",
                        "测试12",
                        "测试13",
                        "测试14"
                };
                new AlertDialog.Builder(this).setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final AlertDialog ad = new AlertDialog.Builder(MyActivity.this).setMessage(items[which]).create();
                        ad.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ad.dismiss();
                            }
                        },3000);
                    }
                }).show();
                break;
            case R.id.notify :
                nofity();
                break;
        }
    }

    private void nofity(){
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setContentInfo("content info");
        notification.setContentText("context text");
        notification.setContentTitle("新通知");
        notification.setSmallIcon(R.drawable.ic_launcher);
        notification.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        Intent intent = new Intent(this,NotificationActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(NotificationActivity.class);
        //taskStackBuilder.addNextIntent(getIntent());
        taskStackBuilder.addNextIntent(intent);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);
        notification.setContentIntent(pendingIntent);
        notification.setSubText("subtext");
        notification.setAutoCancel(true);
        nm.notify(R.drawable.ic_launcher, notification.build());
    }
}
