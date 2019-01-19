package org.opentcs.virtualvehicle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A simple Swing-based client for the capitalization server.
 * It has a main frame window with a text field for entering
 * strings and a textarea to see the results of capitalizing
 * them.
 */
public class CapitalizeClient extends Thread  {

  
  private boolean ct = false;  
  
  private String ipaddress;
  
  private Socket sock;
  
    // reading from keyboard (keyRead object)
  private BufferedReader keyRead;
                              // sending to client (pwrite object)
  private OutputStream ostream ;
  
  private PrintWriter pwrite ;
 
                              // receiving from server ( receiveRead  object)
  private InputStream istream ;
  private BufferedReader receiveRead;
  private String currenposition;
  
  
  
  public CapitalizeClient(String ip) throws IOException {
    
    this.ipaddress = ip;
    this.sock = new Socket(ipaddress, 11000);
    this.keyRead = new BufferedReader(new InputStreamReader(System.in));
    this.ostream = this.sock.getOutputStream(); 
    this.pwrite = new PrintWriter(ostream, true);
    this.istream = this.sock.getInputStream();
    this.receiveRead = new BufferedReader(new InputStreamReader(istream));
    
    
  }
  
 
       
  
  
  
  
  

  @Override
   public void run() 
  {
     // int counter = 0;
     
     //  sendMessage("Hello");
      
     System.out.println("Start, type and press Enter key");
     while(!ct){
       
      // System.out.print(ct);
      // sendMessage("Hi");

         //readCommand();
        // if (counter<=4){
        //  sendMessage("Hello" + counter);
         // counter++;
         
        // }
         
        // else{
           
           //sendMessage("");
           // counter=0;
           
         
       //  }
      
        
       
         
         
         
       
     }
     

 
    }
   
   public void disconnect() throws IOException{
     
     System.out.println("org.opentcs.virtualvehicle.CapitalizeClient.disconnect()");
     try{
     this.ct = true;
     
     }
     finally{
     this.sock.close();
     }
     
   }
   
   public void sendMessage(String message) {
     
     System.out.println("org.opentcs.virtualvehicle.CapitalizeClient.send");
     this.pwrite.println(message);
     this.pwrite.flush();
     
     
     
     
     
   }
   
   public String getPosition() throws IOException {
     
    String position; 
     
    BufferedReader reader  = this.receiveRead;
     
    position = reader.readLine();
    
    return position;
  
   }
    public Boolean readCommand() throws IOException {
      
      System.out.println(this.receiveRead.readLine().toLowerCase());
     if (this.receiveRead.readLine().toLowerCase() == "yes")
     {
        
       return true;
 
       
     }
 
        
     return false;
        
    
    
  }
      
   
   
   
   
   
     public static void main(String[] args) throws Exception {
        System.out.println("The capitalization Client  is running.");
      
             CapitalizeClient c  = new CapitalizeClient("192.168.1.151") ;
         
             c.start();
             
       
            // c.sendMessage("Hi");
         
       
          
  
        
     }

 
        
    }
   