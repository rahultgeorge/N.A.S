package com.nas.upyourcloud;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by rahultitusgeorge on 10/5/16.
 */
public class SocketConnection {
    //Socket connection to the PI

    final int port = 1234;
    final String TAG = "SocketConnection.java";
    DataInputStream in = null;
    DataOutputStream out = null;
    final String ret = "retrieve";
    final String exit = "exit";
    Socket s;
    final String serverAddress = "192.168.1.254";
    byte[] buffer;
    float bytesRead = 0;
    float kb = 0;
    float count = 0;
    long file_size = 0;
    Scanner sc;

    SocketConnection() {
        sc = new Scanner(System.in);
        buffer = new byte[16 * 1024];
    }

    public  boolean connect()
    {
        try {
            s = new Socket(serverAddress, port);
        } catch (Exception e) {
            Log.d(TAG, "Socket connection failed: " + e);
        }
        return s.isConnected();
    }

    public void backUp(ContentResolver resolver,Uri file,String name , long file_size) throws IOException {

        //Send to the pi
        Log.d(TAG, "File name  is: " + name);
        in = new DataInputStream(resolver.openInputStream(file));
        out = new DataOutputStream(s.getOutputStream());
        out.writeUTF(name);
        file_size = (file_size / 1024);
        Log.d(TAG, "File size(KB): " + file_size);
        out.writeLong(file_size);
        out.flush();
        count = 0;
        Log.d(TAG, "Writing has begun");
        while (count < file_size && (bytesRead = in.read(buffer)) > 0) {
            //Log.d(TAG, "Bytes read: " + bytesRead);
            out.write(buffer, 0, (int) bytesRead);
            kb = bytesRead / 1024;
            //Log.d(TAG, "KB read: " + kb);
            count += kb;
        }
        Log.d(TAG,"File Size: "+file_size);
        Log.d(TAG, "Total writen to socket os(KB): " + count);

        //out.flush();


    }

    public void retrieve(String f_name) throws IOException {

        //Retrieve
        out = new DataOutputStream(s.getOutputStream());
        out.writeUTF(ret);
        out.writeUTF(f_name);
        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(new FileOutputStream(in.readUTF()));
        file_size = in.readLong();
        write();

    }

    private void write() throws IOException {

        count = 0;
        Log.d(TAG, "Writing has begun");
        while (Math.ceil(count) < file_size && (bytesRead = in.read(buffer)) > 0) {
            //Log.d(TAG, "Bytes read: " + bytesRead);
            out.write(buffer, 0, (int) bytesRead);
            kb = bytesRead / 1024;
            //Log.d(TAG, "KB read: " + kb);
            count += kb;
        }
        Log.d(TAG,"File Size: "+file_size);
        Log.d(TAG, "Total writen to socket os(KB): " + count);

        out.flush();
    }


    public void exit() throws IOException {
        out.close();
        in.close();
        s.close();
    }
}

