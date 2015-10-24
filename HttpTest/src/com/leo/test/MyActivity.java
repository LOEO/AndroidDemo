package com.leo.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

public class MyActivity extends Activity {
    Handler handler = new Handler();
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WebView webView = (WebView) findViewById(R.id.webview);
        new HttpThread("http://www.baidu.com",handler,webView).start();
    }
}
