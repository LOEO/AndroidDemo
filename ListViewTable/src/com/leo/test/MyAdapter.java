package com.leo.test;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by LT on 2015-06-03.
 */
public class MyAdapter extends BaseAdapter {

    private List<? extends Map<String,?>> mData = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private String[] mFrom;
    private int[] mTo;
    private int mResource;

    public MyAdapter(Context context,List<? extends Map<String,?>> data,int resource,String[] from,int[] to){
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mData = data;
        this.mFrom = from;
        this.mTo = to;
        this.mResource = resource;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position,convertView,parent);
    }

    private View createViewFromResource(int position,View convertView,ViewGroup parent){
        View v;
        if(convertView == null){
            v = mLayoutInflater.inflate(mResource,parent,false);
        }else {
            v = convertView;
        }
        bindView(position,v);
        return v;
    }

    private void bindView(int position,View view){
        Map<String,?> dataSet = mData.get(position);
        if(dataSet == null){
            return;
        }
        String from[] = mFrom;
        int[] to = mTo;
        int count = mTo.length;
        for(int i=0;i<count;i++){
            View v = view.findViewById(to[i]);
            if(v!=null){
                Object data = dataSet.get(mFrom[i]);
                String txt = data == null ? "":data.toString();
                if(txt == null){
                    txt = "";
                }

                if(v instanceof Checkable){
                    if(data instanceof Boolean){
                        ((Checkable) v).setChecked((Boolean)data);
                    }else{
                        setTextView((TextView)v,txt);
                    }
                }
                if(v instanceof TextView){
                    ((TextView) v).setText(txt);
                    setTextView((TextView)v,txt);
                }else if(v instanceof ImageView){
                    if(data instanceof Integer){
                        setImageView((ImageView)v,(Integer)data);
                    }else {
                        setImageView((ImageView)v,txt);
                    }
                }
            }
        }
    }

    private void setTextView(TextView v,String text){
        v.setText(text);
    }

    private void setImageView(ImageView iv,int id){
        iv.setImageResource(id);
    }

    private void setImageView(ImageView iv,String value){
        try {
            iv.setImageResource(Integer.parseInt(value));
        }catch (NumberFormatException e){
            iv.setImageURI(Uri.parse(value));
        }
    }

}
