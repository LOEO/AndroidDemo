package com.leo.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MyActivity extends Activity {
    private Spinner spinner;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String [] items = new String []{
          "第一个",
          "第二个",
          "第三个",
          "第四个",
          "第五个",
        };
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
        spinner.setAdapter(arrayAdapter);

    }
}
