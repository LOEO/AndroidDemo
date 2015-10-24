package com.lt.test;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by LT on 2015-05-17.
 */
public class Test<T extends Activity> {
    private T test;
    public void test(T a){
        Toast.makeText(a,"test",Toast.LENGTH_SHORT).show();
    }
}
