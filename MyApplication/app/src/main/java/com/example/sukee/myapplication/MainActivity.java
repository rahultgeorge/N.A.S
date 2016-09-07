package com.example.sukee.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b2 = (Button)findViewById(R.id.button2);
        Button b = (Button)findViewById(R.id.button);
        final TextView tv = (TextView)findViewById(R.id.textView);
        final TextView tv2 = (TextView)findViewById(R.id.textView2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("a");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv2.setText("a");
            }
        });
    }
}
