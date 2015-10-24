package com.leo.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class MyActivity extends Activity implements View.OnClickListener{

    private Button setting;
    private Button testFileData;
    private Button testSdFileData;
    private Button readDb;
    private DBService dbService;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences sp = getSharedPreferences("test", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("第一条", "first");
        editor.commit();
        setting = (Button) findViewById(R.id.opensetting);
        testFileData = (Button) findViewById(R.id.testFileData);
        testSdFileData = (Button) findViewById(R.id.testSDFileData);
        readDb = (Button) findViewById(R.id.readDb);
        setting.setOnClickListener(this);
        testFileData.setOnClickListener(this);
        testSdFileData.setOnClickListener(this);
        readDb.setOnClickListener(this);
        //创建数据库
        dbService = new DBService(this);

    }


    private void saveBase64() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher).compress(Bitmap.CompressFormat.JPEG, 50, baos);
            String base64 = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences sp = getSharedPreferences("base64", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("base64", base64);
            editor.commit();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.opensetting:
                intent = new Intent(MyActivity.this,MyPreferenceActivity.class);
                break;
            case R.id.testFileData:
                intent = new Intent(MyActivity.this,TestFileData.class);
                break;
            case R.id.testSDFileData:
                intent = new Intent(MyActivity.this,TestSDFileData.class);
                break;
            case R.id.readDb:
                Cursor cursor = dbService.query("select * from [test]", null);
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                        android.R.layout.simple_expandable_list_item_1,
                        cursor,
                        new String[]{"name"},
                        new int[]{android.R.id.text1}
                        );
                ListView listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(simpleCursorAdapter);
                return;
            default:
                intent = new Intent(MyActivity.this,MyPreferenceActivity.class);
        }
        startActivity(intent);
    }

    class Product implements Serializable {
        public int id;
        public String name;
    }
}
