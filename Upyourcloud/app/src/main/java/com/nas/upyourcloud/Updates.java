package com.nas.upyourcloud;

import android.os.Bundle;

public class Updates extends com.nas.upyourcloud.NaviDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_updates,layout);
        pos=5;    }
}
