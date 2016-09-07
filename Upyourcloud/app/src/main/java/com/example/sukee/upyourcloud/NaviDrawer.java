package com.example.sukee.upyourcloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

public class NaviDrawer extends AppCompatActivity {

    private final String[] list={"Home","Backup","My Drive","My Devices","Share","Updates"};
    protected int pos=0;
    private ListView listView;
    private Toolbar toolbar;
    protected FrameLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout = (FrameLayout) findViewById(R.id.frame);
        listView = (ListView) findViewById(R.id.listView);
        setSupportActionBar(toolbar);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=pos)
                switch(i)
                {
                    case 0:

                        startActivity(new Intent(getApplicationContext(),Home.class));
                        finish();
                          break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(),Backup.class));
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),MyDrive.class));
                        finish();
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),MyDevices.class));
                        finish();
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),Share.class));
                        finish();
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),Updates.class));
                        finish();
                        break;
                }
            }
        });
    }


}
