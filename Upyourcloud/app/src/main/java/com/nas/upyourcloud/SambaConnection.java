package com.nas.upyourcloud;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Environment;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rahultitusgeorge on 10/5/16.
 */
public class SambaConnection {


    private NtlmPasswordAuthentication auth;
    private String path;
    private File lFile;
    private SmbFile sFile;
    private SmbFileOutputStream sfos;
    private SmbFileInputStream sfins;
    private InputStream in;
    private FileOutputStream out;
    private byte []buffer;
    private int n;

    SambaConnection() {

       // jcifs.Config.setProperty( "jcifs.netbios.wins", "192.168.1.254" );
        auth = new NtlmPasswordAuthentication("backups:raspberry1");
        path = "smb://RASPBERRYPI/Backup/";
        buffer=new byte[32*1024];
    }

    public void backUp(ContentResolver resolver,Uri file,String name ) throws Exception {

        in = (resolver.openInputStream(file));
        sFile=new SmbFile(path+name,auth);
        sfos=new SmbFileOutputStream(sFile);
        while(( n = in.read( buffer )) > 0 ) {
            sfos.write( buffer, 0, n );
        }

    }

    public String[] getList(String dir) throws Exception
    {
        if(dir==null ||dir.isEmpty())
        sFile=new SmbFile(path,auth);
        else
         sFile=new SmbFile(path+dir+"/",auth);

        return sFile.list();
    }

    public void retrieve(String f_name) throws IOException {

        try {
            sFile=new SmbFile(path+f_name,auth);
            sfins=new SmbFileInputStream(sFile);
            lFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),f_name);
            out=new FileOutputStream(lFile);
            while(( n = sfins.read( buffer )) > 0 ) {
                out.write( buffer, 0, n );
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

