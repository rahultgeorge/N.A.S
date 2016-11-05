import java.io.*;
import java.net.*;

public class Client{	
	static DataInputStream in = null;	
	static DataOutputStream out = null;	
	private static final String ret="retrieve";	
	static int c = 0;
	File file;
	static long file_size=0;
	static Socket s;
	static String fileString;
	
	public static void main(String []args)throws IOException{
		byte[] buffer ;        
		float bytesRead=0;
		float kb=0;
		float count=0;
 		long start,end; 		
		
 		Client client = new Client();
 		s = new Socket("192.168.1.254",1234);
		while(true){
		     if(c==1){
                //Send to the pi
		    	 client.BackUp();
		     }
		     else if(c==2){
                 //Retrieve 
		    	 client.Retrieve();
		     }
		     else{
		    	 System.out.println("Socket connection terminated");
		    	 break;
		     }
		     
		     buffer=new byte[16*1024];
		     count=0;
		     System.out.println("Writing has begun");
		     start=System.nanoTime();
		     while(count<=file_size && (bytesRead=in.read(buffer))>0 ){											
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
	
	private void BackUp(){					
   	 	try {
   	 		file = new File(fileString);
			in = new DataInputStream(new FileInputStream(file));
		} 
   	 	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 	try{
   	 		out= new  DataOutputStream(s.getOutputStream());	   	 	
   	 		out.writeUTF(file.getName());	
   	 		file_size=(file.length()/1024);
   	 		System.out.println("File size(KB): "+file_size);
   	 		out.writeLong(file_size);
			out.flush();
   	 	}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void Retrieve(){
		try {
			out= new  DataOutputStream(s.getOutputStream());		 
			out.writeUTF(ret);
			out.writeUTF(file.toString());
			out.flush();
			in=new DataInputStream(s.getInputStream());          
			out=new DataOutputStream(new FileOutputStream(in.readUTF()));
			file_size=in.readLong();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
