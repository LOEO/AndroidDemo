package com.leo.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Calendar;

/**
 * Created by LT on 2015-07-16.
 */
public class Client {
    private Socket socket;
    private String ip;
    private int port;

    public Client(String ip,int port) {
        this.ip = ip;
        this.port = port;
    }

    public boolean conn() {
        socket = new Socket();
        SocketAddress sa = new InetSocketAddress(ip, port);
        try {
            socket.connect(sa,1000);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String recMsg() {
        BufferedReader br = null;
        String line;
        String result = "";
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((line = br.readLine())!=null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean isConnected(){
        if(socket!=null) {
            return socket.isConnected();
        }
        return false;
    }

    public void sendMsg(String msg) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (socket!=null && socket.isConnected()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket = null;
    }

}
