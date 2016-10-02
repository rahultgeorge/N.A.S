import java.io.*;
import java.net.*;
import java.util.*;

public class Pi
{
        private static final int port = 1234;
        static DataInputStream in = null;
        static DataOutputStream out = null;

        public static void main(String []args)throws IOException
        {
                ServerSocket ss=new ServerSocket(port);
                ss.setSoTimeout(10000);
                System.out.println("Waiting to connect");
                Socket s=ss.accept();
                System.out.println("Connected");
                Scanner sc = new Scanner(System.in);
                int inp;
                File f;
                byte[] buffer;
                int bytesRead,count=0;
		 long file_size=0;
                for(;;)
                {
                        System.out.println("Enter 1 to send, 2 to receive, 3 to exit\n");
                        inp = sc.nextInt();
                        if(inp == 1)
                        {
                                System.out.println("Enter the name of the file to send : ");
                                f = new File(sc.next());
                                in = new DataInputStream(new FileInputStream(f));
                                out = new DataOutputStream(s.getOutputStream());
				  out.writeUTF(f.getName());
				  out.writeLong(f.length());
				  out.flush();
				  file_size=f.length();
                        }
                        else if(inp == 2)
                        {
                                in = new DataInputStream(s.getInputStream());
                             out = new DataOutputStream(new FileOutputStream(in.readUTF()));
				
				file_size=in.readLong();
				
                        }
                        else
		        {
                                s.close();
                        out.close();
                        in.close();
                                break;
                        }

		buffer=new byte[1024];
			count=0;
	                while( count!=file_size && (bytesRead=in.read(buffer))>0 )
		       {
			out.write(buffer,0,bytesRead);
			count+=bytesRead;
		       }
			out.flush();
						
                }
        }
}
