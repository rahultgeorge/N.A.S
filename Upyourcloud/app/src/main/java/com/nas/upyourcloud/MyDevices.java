package com.nas.upyourcloud;

import android.os.Bundle;

public class MyDevices extends NaviDrawer {

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_my_devices,layout);
        pos=3;
    }
}
