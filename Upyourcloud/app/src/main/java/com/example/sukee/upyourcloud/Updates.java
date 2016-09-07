package com.example.sukee.upyourcloud;

import android.os.Bundle;

public class Updates extends NaviDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_updates,layout);
        pos=5;    }
}
