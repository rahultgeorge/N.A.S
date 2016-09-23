
import java.io.*;
import java.net.*;
import java.util.*;

public class Connection
{
    
    private static final int port=1234;
static DataInputStream in=null;
static DataOutputStream out=null;
static String msg;

   public static void main(String []args)throws IOException
   {
     ServerSocket ss=new ServerSocket(port);
     ss.setSoTimeout(10000);
System.out.println("Waiting to connect");
     Socket s=ss.accept();
System.out.println("COnnected");
     Thread  t=new Thread()
        {
            @Override
            public void run() {

              try{
                        while(in!=null)
             { 

       msg=in.readUTF();
          if(msg!=null)
           System.out.println("Client: "+msg);
   
            }
}

catch(Exception e)
{}
        }   
      };

Scanner sc=new Scanner(System.in);
       try{
       if(s!=null)
       {
           in=new DataInputStream(s.getInputStream());
                  out =new DataOutputStream(s.getOutputStream());
           t.start();
        }




while(out!=null)
 {
          
           msg=sc.nextLine();
             if(msg!=null)
{
 System.out.println("Me: "+msg);
            out.writeUTF(msg);
}

        
}

}
      catch(Exception e)
       {
           System.out.println(e);

      }
       
    }     
       
    } 
