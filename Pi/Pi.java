import java.io.*;
import java.net.*;
import java.util.*;

public class Pi
{
        private static final int port = 1234;
	private static final String ret="retrieve";
        private static int client;

        private class FTP extends Thread 
       {
                 Socket s;
                 int inp;
                 File f=null;
                 byte[] buffer;
                 float count=0;
		 float kb=0;
		 float bytesRead = 0;
		 long start,end ;
		 long file_size=0;
		 String f_name=null;
		
		 DataInputStream s_in = null;
         	 DataOutputStream s_out = null;
	 	 DataInputStream f_in = null;
         	 DataOutputStream f_out = null;
		 
          FTP(Socket s)
         {
           this.s=s;
           System.out.println((++client) +" client(s) connected");
          
           buffer=new byte[16*1024];
          }

	
       public void run()
      {
                try
	        {
                 s_in = new DataInputStream(s.getInputStream());
                 s_out = new DataOutputStream(s.getOutputStream()); 
		for(;;)
		{
		 
		if((f_name=s_in.readUTF())!=null && !f_name.equals(ret))
		{
			//Back up
		f_out = new DataOutputStream(new FileOutputStream("Files/"+f_name,false));
		file_size=s_in.readLong();
		System.out.println("FILE TO BE BACKED UP SIZE(KB): "+file_size);
		count=0;
		while( Math.ceil(count)<file_size && (bytesRead=s_in.read(buffer))>0 )
		       {											
			System.out.println("Bytes read: "+ bytesRead);
			f_out.write(buffer,0,(int)bytesRead);
			f_out.flush();
			kb=bytesRead/1024;
			System.out.println("KB read: "+kb);
			count+=kb;
			System.out.println("Total writen into file (KB): "+count);
			System.out.println(Math.ceil(count));
			f_out.flush();
		       }
			System.out.println("Successfull");
			f_out.flush();
		}
               else if(f_name.equals(ret))
		{
			//Retrieve
		  System.out.println("RETRIEVE");
		  f_name=s_in.readUTF();
		  f=new File(f_name);
		  f_in = new DataInputStream(new FileInputStream(f_name));
		  s_out.writeUTF(f_name);
		  file_size=f.length()/(1024);
		  System.out.println("FILE TO BE SENT SIZE(KB): "+file_size);
		  s_out.writeLong(file_size);
                 s_out.flush();
		  count=0;
		 start=System.nanoTime();
		   while( Math.ceil(count)<file_size && (bytesRead=f_in.read(buffer))>0 )
		       {											
			System.out.println("Bytes read: "+ bytesRead);
			s_out.write(buffer,0,(int)bytesRead);
			kb=bytesRead/1024;
			System.out.println("KB read: "+kb);
			count+=kb;
			System.out.println("Total writen to socket os(KB): "+count);
			//s_out.flush();

		       }
			end=System.nanoTime();
			System.out.println("Success: "+( (end-start)/1000000000) );
			s_out.flush();

		}
		
		//send updates condition should be added
		}
		}
		catch(Exception e)
		{
		 System.out.println(e);
		   System.out.println("Socket go bye bye");
		  System.out.println("Socket connection terminated");
	          try
                  {    	
         	  s_out.close();
		  s_in.close();
		  s.close();	 
		  }
                  catch(Exception e1)
                  {
                  
                  }   
                }
           
          System.out.println("Client disconnected now clients: "+(--client));
	  return ;
        } 
        }


        public static void main(String []args)throws IOException
        {
                Pi obj=new Pi();
                System.out.println("Test");
                ServerSocket ss=new ServerSocket(port);
                //ss.setSoTimeout(10000);
                System.out.println("Waiting to connect");
                while(true)
                obj.new FTP(ss.accept()).start();
               // System.out.println("Connected");
		 
		 
		
        }
}
