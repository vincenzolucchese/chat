package com.demo.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }   


    /**
     * Rigourous Test :-)
     * @throws IOException 
     * @throws InterruptedException 
     */
    public void testApp() throws IOException, InterruptedException
    {
    	String messageSend = "Hello world!!!";
    	 AppChat telnetServer = new AppChat();
    	 Thread one = new Thread(telnetServer);
    	 one.start();
    	 ClientTest client1 = new ClientTest();
    	 ClientTest client2 = new ClientTest();    	 
    	 
    	 System.out.println(client1.getIn().readLine());
    	 System.out.println(client2.getIn().readLine());
    	 
    	 client1.getOut().println(messageSend);
    	 String messageToVerify = client2.getIn().readLine();
  	 
    	 client1.closeAll();   	 
    	 client2.closeAll();
    	 Assert.assertTrue(messageToVerify.contains(messageSend));   	
    }
    
    class ClientTest {
	   	 Socket pingSocket = null;
	   	 PrintWriter out = null;
	   	 BufferedReader in = null;
	
	   	ClientTest() throws UnknownHostException, IOException{
	      	 pingSocket = new Socket("localhost", 10000);
	       	 out = new PrintWriter(pingSocket.getOutputStream(), true);
	       	 in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
	   	}
	   	
	   	void closeAll() throws IOException{
	   		this.out.close();
	   		this.in.close();
	   		this.pingSocket.close();
	   	}
	   	 
	 	public Socket getPingSocket() {
			return pingSocket;
		}
		public void setPingSocket(Socket pingSocket) {
			this.pingSocket = pingSocket;
		}
		public PrintWriter getOut() {
			return out;
		}
		public void setOut(PrintWriter out) {
			this.out = out;
		}
		public BufferedReader getIn() {
			return in;
		}
		public void setIn(BufferedReader in) {
			this.in = in;
		}
    }
    
}


