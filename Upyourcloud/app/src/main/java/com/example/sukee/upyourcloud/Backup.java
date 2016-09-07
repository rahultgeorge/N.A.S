package com.example.sukee.upyourcloud;

import android.os.Bundle;

public class Backup extends NaviDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_backup,layout);
        pos=1;

    }
}
