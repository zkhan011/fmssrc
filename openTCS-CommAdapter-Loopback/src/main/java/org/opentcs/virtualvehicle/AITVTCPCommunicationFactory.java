/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle;

import java.io.IOException;
import org.opentcs.data.model.Vehicle;


import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;

/**
 *
 * @author zisha
 */
public class AITVTCPCommunicationFactory {
  
    private static final AITVTCPCommunication TCP_COMMUNICATION = new AITVTCPCommunication();
    private String vehicleId ;
    public String ipaddress;    
    
    public AITVTCPCommunicationFactory(AGVCommAdapter CommunicationAdapter, String vehicleName , String vehicleIp) throws IOException{
       System.out.println("ip Method" + vehicleName);
      
         this.ipaddress = vehicleIp;
         this.vehicleId = CommunicationAdapter.getName();
      
        
        synchronized(TCP_COMMUNICATION){
          System.out.println("Sync Method");
          TCP_COMMUNICATION.connectToCommunicationAdapter(CommunicationAdapter,vehicleName); 
           System.out.println("Sync Method2");
        
         
        
         
        }
        try{
          //Change the com port when needed
          TCP_COMMUNICATION.connect(this.ipaddress,this.vehicleId);
          System.out.println("Connect Method" + this.ipaddress);
        } catch (Exception e){
          System.out.println("Error Unnable to connect on the port " + ipaddress);
          TCP_COMMUNICATION.disconnectAdapter(CommunicationAdapter, this.vehicleId);
          e.printStackTrace();
        }
    }

  

  AITVTCPCommunication getSerialCommunication() {
    return TCP_COMMUNICATION;
  }

  
  
  
  
}
