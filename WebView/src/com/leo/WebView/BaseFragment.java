package com.leo.WebView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by LT on 2015-05-11.
 */
public abstract class BaseFragment extends BFragment implements CordovaInterface{

    protected CordovaPlugin activityResultCallback;
    protected CordovaWebView webView = null;
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    protected boolean keepRunning = true;
    protected boolean activityResultKeepRunning;

  /*  @Override
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return super.getLayoutInflater(savedInstanceState).cloneInContext(new CordovaContext(getActivity(), this));
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(webView == null) {
            //LayoutInflater localInflater = getLayoutInflater(savedInstanceState);
            LayoutInflater localInflater = inflater.cloneInContext(new CordovaContext(getActivity(), this));
            webView = (CordovaWebView) inflater.inflate(R.layout.webview, container, false);
        }
        Config.init(getActivity());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.requestFocus();
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(false);

        webView.getSettings().setDomStorageEnabled(true);
        //webView.getSettings().setPluginsEnabled(true);

        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);

        //webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new com.leo.WebView.WebViewClient(this,webView));
        return webView;
    }

    protected void setWebView(CordovaWebView webView){
        this.webView = webView;
    }


    protected class WebChromeClient extends android.webkit.WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            getActivity().setProgress(newProgress*100);
        }
    }


    @Override
    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        this.activityResultCallback = command;
        this.activityResultKeepRunning = this.keepRunning;
        // If multitasking turned on, then disable it for activities that return
        // results
        if (command != null) {
            this.keepRunning = false;
        }
        // Start activity
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public ExecutorService getThreadPool() {
        return threadPool;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        CordovaPlugin callback = this.activityResultCallback;
        if (callback != null) {
            callback.onActivityResult(requestCode, resultCode, intent);
        }
    }

    @Override
    public void setActivityResultCallback(CordovaPlugin plugin) {
        this.activityResultCallback = plugin;
    }

    @Override
    public void onDestroyView() {
        //解决 leaked IntentReceiver 异常  2015-05-14 16:47:24 {by lt}
        if(webView!= null)
            webView.handleDestroy();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView.pluginManager != null) {
            webView.pluginManager.onDestroy();
        }
    }
}
