package com.leo.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.*;

/**
 * Created by LT on 2015-05-21.
 * 使用 文件流 向SD卡 写入文件
 */
public class TestSDFileData extends Activity implements View.OnClickListener{

    Button button;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_sd_file_data);
        button = (Button) findViewById(R.id.viewImg);
        button.setOnClickListener(this);
        writeFile();
    }

    private void writeFile(){
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            fos = new FileOutputStream(android.os.Environment.getExternalStorageDirectory()+"/img.jpg");
            is = getResources().getAssets().open("1.png");
            byte[] bytes = new byte[1024];
            int count =0;
            while ((count = is.read(bytes)) > 0 ){
                fos.write(bytes,0,count);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(fos!=null){
                    fos.close();
                }
                if(is != null){
                    is.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void readFile(){
        FileInputStream fis = null;
        img = (ImageView) findViewById(R.id.img);
        try {
            fis = new FileInputStream(android.os.Environment.getExternalStorageDirectory() + "/img.jpg");
            Bitmap bitMap = BitmapFactory.decodeStream(fis);
            img.setImageBitmap(bitMap);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        readFile();
    }
}
