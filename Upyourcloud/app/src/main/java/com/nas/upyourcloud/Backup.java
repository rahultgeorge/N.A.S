package com.nas.upyourcloud;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Backup extends NaviDrawer {

    Uri file_path;
    String name;
    static final int FILE_PICK = 1;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_backup, layout);
        pos = 1;
        Button b = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, FILE_PICK);
            }
        });


    }

    void newThread() {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    SambaConnection sambaConnection = new SambaConnection();

                        sambaConnection.backUp(getContentResolver(), file_path, name);


                } catch (Exception e) {
                    Log.d("TEST", "thread returned: " + e);

                    return;
                }

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_PICK)

            if (resultCode == RESULT_OK) {
                file_path=data.getData();
                Cursor cursor=getContentResolver().query(file_path,null,null,null,null);
                cursor.moveToFirst();
                name=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Toast.makeText(getApplicationContext(),"File: "+name,Toast.LENGTH_SHORT).show();
                newThread();
                thread.start();
            }
    }
}
