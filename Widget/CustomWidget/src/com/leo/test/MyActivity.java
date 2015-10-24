package com.leo.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Context context = this;
        MyTitleBar myTitleBar = (MyTitleBar) findViewById(R.id.titleBar);
        myTitleBar.setOnTitleBarClickListener(new MyTitleBar.TitleBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(context,"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(context,"right",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
