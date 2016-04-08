import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Date;

/**
 * Connect to a time server and get time from it as a Date object.
 */
public class TimeClient
{
    public static void main(String args[]) 
    {
	if (args.length != 4) {
	    System.err.println("Usage: java TimeClient <serverhost> <port> <serverhost2> <port2>");
	    System.exit(1);
	}
	String host1 = args[0];
	int port1 = Integer.parseInt(args[1]);
	String host2 = args[2];
	int port2 = Integer.parseInt(args[3]);
	boolean failed = false;
	
	try {
	    Socket s = new Socket(host1, port1);
	    InputStream in = s.getInputStream();
	    ObjectInput oin = new ObjectInputStream(in);

	    Date date = (Date) oin.readObject();
	    System.out.println("Time on host " + host1 + " is " + date);
	} catch (IOException e1) {
	    failed = true;
	    System.out.println(e1);
	} catch (ClassNotFoundException e2) {
	    failed = true;
	    System.out.println(e2);
	}
	
	if (failed) {
	    try {
	    	Socket s = new Socket(host2, port2);
	    	InputStream in = s.getInputStream();
	    	ObjectInput oin = new ObjectInputStream(in);

	    	Date date = (Date) oin.readObject();
	    	System.out.println("Time on host " + host2 + " is " + date);
	    } catch (IOException e1) {
	    	System.out.println(e1);
	    } catch (ClassNotFoundException e2) {
	    	System.out.println(e2);
	    }
	}
    }
}
