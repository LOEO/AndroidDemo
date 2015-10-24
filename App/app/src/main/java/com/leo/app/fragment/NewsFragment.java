package com.leo.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leo.app.R;
import com.leo.app.adapter.MyAdapter;

/**
 * Created by LT on 2015/10/14 0014.
 */
public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar
                        .make(container, "提示", Snackbar.LENGTH_LONG);
                snackbar.setAction("action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        String[] s = new String[60];
        for(int i='A',j=0;j<s.length;j++){
            s[j] = String.valueOf((char)(i+j));
        }
        recyclerView.setAdapter(new MyAdapter(s));
        return view;
    }
}
