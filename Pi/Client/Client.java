import java.io.*;

import java.net.*;

import java.util.*;


public class Client

{
	
private static final int port = 1234;

static DataInputStream in = null;
	
static DataOutputStream out = null;
	
private static final String ret="retrieve";
	
private static final String exit="exit";

	
public static void main(String []args)throws IOException
	
{
	
byte[] buffer ;
           
float bytesRead=0;
		
float kb=0;
		
float count=0;
		
long file_size=0;
 		
long start,end;
Scanner sc=new Scanner(System.in);
		
String f_name;
	
Socket s = new Socket("192.168.1.254",1234);
     
while(true)
              
{
             
System.out.println("Welcome Laptop");
		
System.out.println("Enter 1 to backup to the pi");
		
System.out.println("2 to retrieve from the pi");
		
int c=sc.nextInt();
		               
		
if(c==1)
              
 {
                //Send to the pi
		
        System.out.println("Enter the file name to send");
	
        File file = new File(sc.next());			
	System.out.println("File name entered is: "+file.getName());
	
	in = new DataInputStream(new FileInputStream(file));
	
	out= new  DataOutputStream(s.getOutputStream());
		 
	out.writeUTF(file.getName());	

	file_size=(file.length()/1024);
	
	System.out.println("File size(KB): "+file_size);
	
	out.writeLong(file_size);	
		
        out.flush();
    
           }

else if(c==2)
	
	{
                 //Retrieve 

			System.out.println("Enter the file name to retrieve from the PI");
			
                        f_name=sc.next();

 			out= new  DataOutputStream(s.getOutputStream());
			
                        out.writeUTF(ret);

			out.writeUTF(f_name);
			out.flush();

			in=new DataInputStream(s.getInputStream());          
		
                    	out=new DataOutputStream(new FileOutputStream(in.readUTF()));

			file_size=in.readLong();
		
         }


else
 
    {
		
                       System.out.println("Socket connection terminated");
     
                       break;
    
            }

		
                buffer=new byte[1024*1024];

		count=0;
               
                System.out.println("Writing has begun");

		start=System.nanoTime();
 	        while(count<=file_size && (bytesRead=in.read(buffer))>0 )
		       
   {											
                System.out.println("Bytes read: "+ bytesRead);

		out.write(buffer,0,(int)bytesRead);
	
	        kb=bytesRead/1024;

		System.out.println("KB read: "+kb);

		count+=kb;

		System.out.println("Total writen to socket os(KB): "+count);	      
   }	
		end=System.nanoTime();
		System.out.println("Time taken to read (MBS): "+ (   (file_size/1024)   /   ((end-start)/1000000000) ));
      
		out.flush();
   
       }
	
	       out.close();

               in.close();
		
	       s.close();

	}
}
		