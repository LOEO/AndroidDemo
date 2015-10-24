package com.leo.WebView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LT on 2015-06-29.
 */
public class BFragment extends Fragment {
    private boolean isVisible=false;
    //用来缓存Fragment中的view，防止多个Fragment之前切换，重复实例化VIEW  2015-07-01 21:15:01 {by lt}
    protected View rootView;
    protected void onVisible(){}
    protected void onHidden(){}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setUserVisibleHint(getUserVisibleHint());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        boolean b = isVisible();
        if(isVisibleToUser ){
            if(isVisible() && !isVisible){
                onVisible();
                isVisible = true;
            }
        }else{
            onHidden();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        isVisible = false;
        if(rootView!=null){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }
}
