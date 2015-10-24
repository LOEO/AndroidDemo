package com.leo.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by LT on 2015-05-21.
 * 使用openFileInput和openFileOutput
 */
public class TestFileData extends Activity implements View.OnClickListener{

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_file_data);
        button = (Button) findViewById(R.id.read);
        button.setOnClickListener(this);
        OutputStream os = null;
        try {
             os = openFileOutput("file.txt",MODE_PRIVATE);
            String str = "测试写入文件";
            os.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) findViewById(R.id.text);
        InputStream is = null;
        try {
            is = openFileInput("file.txt");
            byte[] bytes = new byte[100];
            int count = is.read(bytes);
            String str = new String (bytes,0,count,"utf-8");
            textView.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
       }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
