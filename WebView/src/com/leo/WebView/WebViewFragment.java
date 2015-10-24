package com.leo.WebView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.Toast;
import org.apache.cordova.CordovaWebView;

/**
 * Created by LT on 2015-07-10.
 */
public class WebViewFragment extends BaseFragment{

    @Override
    public void onVisible() {
        Toast.makeText(WebViewFragment.this.getActivity(),"显示",Toast.LENGTH_SHORT).show();
        webView.loadUrl("http://192.168.1.117:8081/tpl/nis/index.html?url=case_other");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater localInflater = inflater.cloneInContext(new CordovaContext(getActivity(), this));
        View view = localInflater.inflate(R.layout.webview, container, false);
        final CordovaWebView webView = (CordovaWebView) view.findViewById(R.id.webview);
        setWebView(webView);

        webView.addJavascriptInterface(this,"test");
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.clearCache(true);
                Toast.makeText(WebViewFragment.this.getActivity(),"加载",Toast.LENGTH_SHORT).show();
                webView.loadUrl("http://192.168.1.113:8081/tpl/nis/index.html?url=case_info");
            }
        });
        /*final CordovaWebView wv = (CordovaWebView) view.findViewById(R.id.webview);
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WebViewFragment.this.getActivity(),"加载",Toast.LENGTH_SHORT).show();
                webView.loadUrl("http://192.168.1.117:8081/tpl/nis/index.html");
                 wv.loadUrl("http://192.168.1.117:8081/tpl/nis/index.html");
            }
        });



        //wv.addJavascriptInterface(this,"test");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.requestFocus();
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        wv.setHorizontalScrollBarEnabled(false);
        wv.setHorizontalScrollbarOverlay(true);
        wv.setVerticalScrollBarEnabled(false);
        wv.setVerticalScrollbarOverlay(true);

        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setBuiltInZoomControls(false);

        wv.getSettings().setDomStorageEnabled(true);
        //webView.getSettings().setPluginsEnabled(true);

        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setAllowFileAccess(true);

        //webView.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.setWebViewClient(new com.leo.WebView.WebViewClient(this,wv));*/
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @JavascriptInterface
    public void test(String s ) {
        Log.i("sss", s);
        //Toast.makeText(this.getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Object onMessage(String s, Object o) {
        return null;
    }
}
