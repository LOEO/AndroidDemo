package com.leo.WebView;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;

import java.util.concurrent.ExecutorService;

public class CordovaContext extends ContextWrapper implements CordovaInterface {
    CordovaInterface cordova;

    public CordovaContext(Context base, CordovaInterface cordova) {
        super(base);
        this.cordova = cordova;
    }

    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        cordova.startActivityForResult(command, intent, requestCode);
    }

    public void setActivityResultCallback(CordovaPlugin plugin) {
        cordova.setActivityResultCallback(plugin);
    }

    public Activity getActivity() {
        return cordova.getActivity();
    }

    public Object onMessage(String id, Object data) {
        return cordova.onMessage(id, data);
    }

    public ExecutorService getThreadPool() {
        return cordova.getThreadPool();
    }
}