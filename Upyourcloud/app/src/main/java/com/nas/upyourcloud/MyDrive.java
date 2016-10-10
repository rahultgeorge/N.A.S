package com.nas.upyourcloud;

import android.os.Bundle;



import android.os.Bundle;

public class MyDrive extends NaviDrawer {

    //To see user's files and retrieve
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_drive,layout);
        pos=2;
    }
}
