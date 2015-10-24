package com.leo.WebView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by LT on 2015-07-15.
 */
public class WebViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview1);
        final WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearCache(true);
                Toast.makeText(WebViewActivity.this, "加载", Toast.LENGTH_SHORT).show();
                webView.loadUrl("http://192.168.1.113:8081/tpl/nis/index.html?url=case_info");
            }
        });
    }
}
