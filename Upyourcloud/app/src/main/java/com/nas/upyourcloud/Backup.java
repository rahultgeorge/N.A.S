package com.nas.upyourcloud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class Backup extends NaviDrawer {

    String file_path;
    static final int FILE_PICK=1;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_backup,layout);
        pos=1;
        Button b=(Button) findViewById(R.id.button2);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent,FILE_PICK);
            }
        });





    }
      void newThread()
      {
          thread =new Thread()
          {
              @Override
              public void run() {
                  super.run();
                  try {
                      SocketConnection socketConnection = new SocketConnection();
                      if (socketConnection.connect()) {
                          socketConnection.backUp(new File(file_path));
                          socketConnection.exit();
                      }
                  }
                  catch (Exception e)
                  {
                      Log.d("TEST", "thread returned: "+e);

                      return;
                  }

              }
          };
      }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==FILE_PICK)

            if (resultCode==RESULT_OK) {

                file_path="storage/emulated/0/Video/Dasvidaniya.mp4";
                Toast.makeText(getApplicationContext(),"File chosen: "+file_path,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"File size: "+(new File(file_path)).getAbsolutePath(),Toast.LENGTH_SHORT).show();
                newThread();
                thread.start();
                thread=null;

            }


    }
}
