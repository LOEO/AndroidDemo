package com.leo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.app.R;
import com.leo.app.bean.MenuItem;

import java.util.List;

/**
 * Created by LT on 2015-08-02.
 */
public class LeftMenuAdapter extends BaseAdapter {
    private List<MenuItem> datas;
    private Context context;
    public LeftMenuAdapter(Context context,List<MenuItem> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left_menu, null,false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(datas.get(position).getText());
        viewHolder.icon.setImageResource(datas.get(position).getIcon());
        return convertView;
    }

    private static class ViewHolder {
        ImageView icon;
        TextView text;
    }
}
