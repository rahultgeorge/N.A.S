package com.nas.upyourcloud;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MyDrive extends NaviDrawer {

    //To see user's files and retrieve
    ListView listView;
    InputStream is;
    String line,response="";
    ArrayList<String> list;
    JSONArray array;
    int i=0;
    Thread socket_thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_drive,layout);
        pos=2;
        listView=(ListView) findViewById(R.id.listView2);
        list=new ArrayList<>();

        Thread get_list=new Thread()
        {
            @Override
            public void run() {
                super.run();
                try {
                    URL ip = new URL("http://192.168.1.254/List.php");

                    HttpURLConnection urlConnection = (HttpURLConnection) ip.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.getOutputStream().write("username=raspberry&password=pi".getBytes());
                    urlConnection.getOutputStream().close();
                    is = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                    while ((line = bufferedReader.readLine()) != null)
                        response += line;

                    Log.d("Response", response);
                    array = new JSONArray(response);
                    while (i < array.length())
                    {
                        String val=array.getString(i);
                        if(!(val.equals(".")||val.equals("..")))
                        list.add(val);
                        i++;

                    }
                    setList();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        get_list.start();
    }
    void newThread(final int pos) {
        socket_thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    SocketConnection socketConnection = new SocketConnection();
                    if (socketConnection.connect()) {
                        socketConnection.retrieve(list.get(pos));
                        socketConnection.exit();
                    }
                } catch (Exception e) {
                    Log.d("TEST", "thread returned: " + e);

                    return;
                }

            }
        };
    }

    private void setList()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        newThread(i);
                        socket_thread.start();
                    }
                });
            }
        });

    }
}
