package com.example.sukee.upyourcloud;

import android.os.Bundle;

public class Home extends NaviDrawer {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home,layout);
        pos=0;
    }

}
