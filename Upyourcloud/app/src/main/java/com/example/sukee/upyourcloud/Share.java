package com.example.sukee.upyourcloud;

import android.os.Bundle;

public class Share extends NaviDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_share,layout);
        pos=4;    }
}
