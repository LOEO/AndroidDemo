package com.leo.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by LT on 2015-06-03.
 */
public class GridView extends LinearLayout {
    private String[] columns;
    private ListView body;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public GridView(Context context) {
        super(context);
    }

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GridView(Context context,String[] columns,ListView body){
        super(context);
        mContext = context;
        this.columns = columns;
        this.body = body;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private void createView(){
        LinearLayout header = new LinearLayout(mContext);

    }

    private void createHeader(LinearLayout header){

        for(int i=0;i<columns.length;i++){
            TextView tv = new TextView(mContext);
            tv.setGravity(Gravity.CENTER);

        }
    }

    private void createBody(){

    }
}
