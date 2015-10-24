package com.leo.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.leo.app.adapter.LeftMenuAdapter;
import com.leo.app.bean.MenuItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LT on 2015-08-01.
 */
public class LeftMenuFragment extends ListFragment {
    private String[] items = {"测试1", "下载", "列表", "卡片", "NavigationView", "测试6"};
    private List<MenuItem> menuItems;
    private LeftMenuAdapter leftMenuAdapter;
    private OnMenuSelectedListener onMenuSelectedListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuItems = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setIcon(R.mipmap.ic_launcher);
            menuItem.setText(items[i]);
            menuItems.add(menuItem);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        leftMenuAdapter = new LeftMenuAdapter(this.getActivity(),menuItems);
        setListAdapter(leftMenuAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        onMenuSelectedListener.onMenuSelected(menuItems.get(position));
    }


    public OnMenuSelectedListener getOnMenuSelectedListener() {
        return onMenuSelectedListener;
    }

    public void setOnMenuSelectedListener(OnMenuSelectedListener onMenuSelectedListener) {
        this.onMenuSelectedListener = onMenuSelectedListener;
    }

    public interface OnMenuSelectedListener{
        void onMenuSelected(MenuItem menuItem);
    }
}
