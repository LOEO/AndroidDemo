package com.leo.WebView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewClient;
import org.apache.cordova.api.CordovaInterface;

//如果继承自WebViewClient,CordovaWebView的loadUrlIntoView方法中会报一个空指针错误  2015-05-14 16:47:32 {by lt}
public class WebViewClient extends CordovaWebViewClient {
    ProgressDialog dialog = null;
    private Context context;

    public WebViewClient(CordovaInterface cordova) {
        super(cordova);
        this.context = cordova.getActivity();
    }

    public WebViewClient(CordovaInterface cordova, CordovaWebView view) {
        super(cordova, view);
        this.context = cordova.getActivity();
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        dialog = ProgressDialog.show(context,null,"页面加载中，请稍后..");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }

    }
}