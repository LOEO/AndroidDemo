package com.leo.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private MyAdapter myAdapter;
    private ListView mLv;
    private List<Map<String,Object>> list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        intiView();
    }

    private void intiView() {
        mLv = (ListView) findViewById(R.id.lv);
        initData();
        myAdapter = new MyAdapter(this,list,R.layout.list_item,
                new String[]{"id","job","addr","student"},
                new int[]{R.id.id,R.id.job,R.id.addr,R.id.student});
        mLv.setAdapter(myAdapter);
    }

    private void initData() {
        list = new ArrayList<>();
        for(int i=0;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",i);
            map.put("job","job"+i);
            map.put("addr","addr"+i);
            map.put("student","student"+i);
            list.add(map);
        }
    }
}
