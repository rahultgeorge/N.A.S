package com.example.sukee.upyourcloud;

import android.os.Bundle;

public class MyDrive extends NaviDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_drive,layout);
        pos=2;
    }
}
