import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	private static final int port = 1234;
	static DataInputStream in = null;
	static DataOutputStream out = null;

	public static void main(String []args)throws IOException
	{
               String f_name;
		byte[] buffer ;
               int bytesRead=0,count=0;
		long file_size=0;
 		Scanner sc=new Scanner(System.in);
		Socket s = new Socket("192.168.1.35", port);
               while(true)
              {
               System.out.println("Welcome Laptop");
		System.out.println("Enter 1 to backup to the pi");
		System.out.println("2 to retrieve from the pi");
		int c=sc.nextInt();
		System.out.println("Enter the file name ");
		f_name=sc.next();
	        File file = new File(f_name);
		if(c==1)
               {
                //Send to the pi
		in = new DataInputStream(new FileInputStream(file));
		out= new  DataOutputStream(s.getOutputStream());
		out.writeUTF(f_name);	
		out.writeLong(file.length());	
		out.flush();
		file_size=file.length();
               }
		else if(c==2)
		{
                 //Receive
			in=new DataInputStream(s.getInputStream());          
			out=new DataOutputStream(new FileOutputStream(in.readUTF()));
			file_size=in.readLong();
		}
               else
                {
			break;
                }

		buffer=new byte[s.getSendBufferSize()];
		count=0;
	                while( count!=file_size && (bytesRead=in.read(buffer))>0 )
		       {
			
			out.write(buffer,0,bytesRead);
			count+=bytesRead;
		       }	      
		out.flush();
	        System.out.println("End of loop");
          }
		out.close();
               in.close();
		s.close();
				 	}
}
		