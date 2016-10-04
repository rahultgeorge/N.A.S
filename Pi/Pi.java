import java.io.*;
import java.net.*;
import java.util.*;

public class Pi
{
        private static final int port = 1234;
	private static final String ret="retrieve";
	private static final String exit="exit";

        public static void main(String []args)throws IOException
        {
                ServerSocket ss=new ServerSocket(port);
                ss.setSoTimeout(10000);
                Scanner sc = new Scanner(System.in);
                int inp;
                File f=null;
                byte[] buffer;
                float count=0;
		 float kb=0;
		 float bytesRead = 0;
		 long file_size=0;
		 String f_name=null;
		 buffer=new byte[1024*1024];
		 DataInputStream s_in = null;
         	 DataOutputStream s_out = null;
	 	 DataInputStream f_in = null;
         	 DataOutputStream f_out = null;
		 System.out.println("Waiting to connect");
                Socket s=ss.accept();
                System.out.println("Connected");
		 s_in = new DataInputStream(s.getInputStream());
		 s_out = new DataOutputStream(s.getOutputStream());

		for(;;)
		{
		 try
	{
		if((f_name=s_in.readUTF())!=null && !f_name.equals(ret))
		{
			//Back up
		f_out = new DataOutputStream(new FileOutputStream(f_name));
		file_size=s_in.readLong();
		System.out.println("FILE TO BE BACKED UP SIZE(KB): "+file_size);
		count=0;
		while( Math.ceil(count)<file_size && (bytesRead=s_in.read(buffer))>0 )
		       {											System.out.println("Bytes read: "+ bytesRead);
			f_out.write(buffer,0,(int)bytesRead);
			kb=bytesRead/1024;
			System.out.println("KB read: "+kb);
			count+=kb;
			System.out.println("Total writen into file (KB): "+count);
			System.out.println(Math.ceil(count));
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
		  count=0;
		   while( Math.ceil(count)<file_size && (bytesRead=f_in.read(buffer))>0 )
		       {											System.out.println("Bytes read: "+ bytesRead);
			s_out.write(buffer,0,(int)bytesRead);
			kb=bytesRead/1024;
			System.out.println("KB read: "+kb);
			count+=kb;
			System.out.println("Total writen to socket os(KB): "+count);

		       }
			
			System.out.println("Success");
			s_out.flush();

		}
		else if(f_name.equals(exit))
		{
		 //exit condition
		  System.out.println("Socket go bye bye");
		  s_out.close();
		  s_in.close();
		  s.close();
		}
		//send updates condition should be added
		}
		catch(EOFException e)
		{
		 System.out.println(e);
		 System.out.println("Socket connection terminated");
		 break;
		}


		}
        }
}
