package com.nas.upyourcloud;

import android.os.Bundle;



import android.os.Bundle;

public class Share extends NaviDrawer {

    //To send to other devices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_share,layout);
        pos=4;    }
}
