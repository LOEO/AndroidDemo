package com.lt.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AbsListView.OnScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity implements AdapterView.OnItemClickListener,OnScrollListener{
    /**
     * Called when the activity is first created.
     */
    private ArrayAdapter arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private ListView listView;
    List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter 用法
        //arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,new String[]{"测试1","测试1","测试1","测试1"});
        //listView.setAdapter(arrayAdapter);

        //SimpleAdapter 用法
        addData();
        simpleAdapter = new SimpleAdapter(this,list,R.layout.item,new String[]{"img","txt"},new int[]{R.id.img,R.id.txt});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        listView.setSelection(8);
    }

    private void addData(){
        for(int i=0;i<10000;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",R.drawable.man);
            map.put("txt","测试"+i);
            map.put("msg","信息"+i);
            list.add(map);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String txt = listView.getItemAtPosition(position).toString();
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("tag","height="+view.getHeight()+" scrollHeight="+view.getScrollY());
                if(view.getLastVisiblePosition() == view.getCount()-1){
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("img",R.drawable.tab_weixin_normal);
                    map.put("txt","新增"+System.currentTimeMillis());
                    list.add(map);
                    simpleAdapter.notifyDataSetChanged();
                }
                break;
            case SCROLL_STATE_TOUCH_SCROLL:

                break;
            case SCROLL_STATE_IDLE:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
