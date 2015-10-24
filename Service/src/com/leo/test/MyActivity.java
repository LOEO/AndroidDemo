package com.leo.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity implements View.OnClickListener{
    Button start;
    Button end;
    Button bind;
    Button unbind;
    MyService myService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder)service).getService();
            Toast.makeText(MyActivity.this, "onServiceConnected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
            Toast.makeText(MyActivity.this, "onServiceDisconnected", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        start = (Button) findViewById(R.id.start);
        end = (Button) findViewById(R.id.end);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MyService.class);
        switch (v.getId()){
            case R.id.start:
                startService(intent);
                break;
            case R.id.end:
                stopService(intent);
                break;
            case R.id.bind:
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(serviceConnection);
                break;
        }
    }
}
