package com.leo.test;

import android.os.Handler;
import android.webkit.WebView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LT on 2015-05-24.
 */
public class HttpThread extends Thread {
    String url;
    Handler handler;
    WebView webView;
    public HttpThread(String url,Handler handler,WebView webView){
        this.url = url;
        this.handler = handler;
        this.webView = webView;
    }

    @Override
    public void run() {
        try {
            URL u = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) u.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            final StringBuffer sb = new StringBuffer();
            String str = "";
            while((str = br.readLine()) != null){
                sb.append(str);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadData(sb.toString(),"text/html;charset=utf-8","utf-8");
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
