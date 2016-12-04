package com.nas.upyourcloud;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyDrive extends NaviDrawer {

    //To see user's files and retrieve
    ListView listView;
    String[] list;
    int i=0;
    Thread retrieveThread;
    Thread getListThread;
    String path="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_drive,layout);
        pos=2;
        listView=(ListView) findViewById(R.id.listView2);

        String[] perms = {"android.permission. WRITE_EXTERNAL_STORAGE"};

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck== PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    12);
        }
        getNewList(null);
        getListThread.start();

    }

    void getNewList(final String dir)
    {
        getListThread =new Thread()
        {
            @Override
            public void run() {
                super.run();
                try {
                    SambaConnection sambaConnection=new SambaConnection();
                    list=sambaConnection.getList(dir);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                setList();
            }
        };
    }

    void retrieveNewItem(final int pos) {
        retrieveThread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    SambaConnection sambaConnection = new SambaConnection();
                      if(list[pos].contains(".")) {
                          sambaConnection.retrieve(path + list[pos]);
                          showNotification(list[pos]);
                      }
                    else {
                          path+=list[pos];
                          getNewList(list[pos]);
                          getListThread.start();
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
                listView.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        retrieveNewItem(i);
                        retrieveThread.start();
                    }
                });
            }
        });

    }

    private void showNotification(String name)
    {

        Notification.Builder builder=new Notification.Builder(getBaseContext());
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setContentTitle("Downloaded:"+name);
        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(11, builder.build());
    }

}
