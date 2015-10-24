package com.leo.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener{
    Button button;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.opentel);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //����绰
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:12345678"));
        //�����봫�벦�Ž���
        intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:12345678"));
        //����ַ
        intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
        //  ��email�ͻ��� ����email��ַ
        intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:286269159@qq.com"));
        startActivity(intent);

    }
}
