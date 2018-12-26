/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import org.opentcs.data.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author zisha
 */
class AITVTCPCommunication {
  
    private static ConcurrentMap<String,AGVCommAdapter> idToNameMap;
    private static ConcurrentMap<AGVCommAdapter,String> NameToIdMap;
    private static ConcurrentMap<String , CapitalizeClient> ConnectedCLient;
    
    private static InputStream in;
    private static OutputStream out;
    
  
    
   
    private String sockname;
    
    private static boolean crun = false;

    static byte[] defaultMessage = {'A','G','V',0,0,0,0,0,0,0};    
    static byte[] outMessage = {'A','G','V',0,0,0,0,0,0,0};    
    private static final Logger LOG = LoggerFactory.getLogger(AITVTCPCommunication.class);
  
  public AITVTCPCommunication(){
  
      idToNameMap = new ConcurrentHashMap<>();
      NameToIdMap = new ConcurrentHashMap<>();      
      ConnectedCLient = new ConcurrentHashMap<>();
   
    
  }
  
  
  
  

 public static synchronized void  connectToCommunicationAdapter(AGVCommAdapter CommunicationAdapter,String vehicleName ) {
  //assert(!idToNameMap.containsKey(vehicleName) && !NameToIdMap.containsKey(CommunicationAdapter));
    idToNameMap.put(vehicleName,CommunicationAdapter);
    NameToIdMap.put(CommunicationAdapter,vehicleName);
    
    System.out.println("Connect method called");
  }

  synchronized void connect(String ipaddress, String vehicleId) throws InterruptedException, Exception {
    System.out.println("Called Connection" + ipaddress);
    sockname = vehicleId; 
   // EchoClient sockname =  new EchoClient(ipaddress, 11000);
   
    System.out.println("Client: " + sockname);
   
    CapitalizeClient c = new CapitalizeClient(ipaddress);
    c.start();
    c.sendMessage("Hello this is" + vehicleId);
    ConnectedCLient.put(vehicleId,c);
    
    System.out.println("Connceted Client" + c);
    
      idToNameMap.get(vehicleId).getProcessModel().setVehicleState(Vehicle.State.IDLE);
      idToNameMap.get(vehicleId).initVehiclePosition(c.getPosition());
    
    
    
  
    
    
   // sockname.start();
    
    //sock = new Socket(ipaddress, 11000);
    // reading from keyboard (keyRead object)
    //BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
    // sending to client pwrite object)
    // out = sock.getOutputStream();
    // PrintWriter pwrite = new PrintWriter(out, true);
    // receiving from server ( receiveRead  object)
    // in = sock.getInputStream();
    // BufferedReader receiveRead = new BufferedReader(new InputStreamReader(in));
    //  pwrite.println("FIRST <EOF>");       // sending to server
    // System.out.println("Start, type and press Enter key");
    //  String receiveMessage, sendMessage;
    // for ( int i =0 ; i<5;i++)
    // {
    // sendMessage = keyRead.readLine();  // keyboard reading
    //  pwrite.println(sendMessage);       // sending to server
    //  pwrite.flush();                    // flush the data
    // if((receiveMessage = receiveRead.readLine()) != "NULL"  || (receiveMessage = receiveRead.readLine())!= null  ) //receive from server
    // {
    //System.out.println(receiveMessage); // displaying at DOS prompt
    //}
    // }
                     
  }
   public static void disconnectAdapter(AGVCommAdapter adapter, String vehicleId) throws IOException{
     
     
        
     
     
     
       System.out.println("I have this adpter:" + adapter);
      
       
       
       
       
       
     
       
      
       
       
       
     
      
     // adapter.disable();
       
       if(vehicleId != null){
          System.out.println("Disconnecting adapter");
          ConnectedCLient.get(vehicleId).disconnect();
            idToNameMap.get(vehicleId).getProcessModel().setVehicleState(Vehicle.State.UNAVAILABLE);
          if(NameToIdMap.isEmpty()){
             System.out.println("removing instance adapter");
             NameToIdMap.remove(adapter);
            idToNameMap.remove(vehicleId);
             ConnectedCLient.remove(vehicleId);
        
          
         }
         
       
        
       }
    }
  
   public static void sendCommand(AGVCommAdapter adapter, String vehicleId) throws IOException{
     
       
       System.out.println("sending message to adpter" + adapter);
       ConnectedCLient.get(NameToIdMap.get(adapter)).sendMessage(vehicleId);
        
          
         
         
       
        
       }
    }
   
   
   
   
   
   
   
  
  
  
  
  
  

