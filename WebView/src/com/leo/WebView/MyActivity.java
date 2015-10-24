package com.leo.WebView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
/*        WebView wv = (WebView) findViewById(R.id.webview);
        wv.loadUrl("http://192.168.1.117:8081/tpl/nis/index.html");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.addJavascriptInterface(this,"test");
        wv.setWebChromeClient(new WebChromeClient());*/
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content,new WebViewFragment()).commit();
        Intent intent = new Intent(this,WebViewActivity.class);
        startActivity(intent);
    }

    @JavascriptInterface
    public void test(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
