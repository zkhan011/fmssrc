/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle;

import java.io.IOException;

/**
 *
 * @author zisha
 */
public class AITVTCPCommunicationFactory {

  private static final AITVTCPCommunication TCP_COMMUNICATION = new AITVTCPCommunication();
  private String vehicleId;
  public String ipaddress;

  public AITVTCPCommunicationFactory(AGVCommAdapter CommunicationAdapter, String vehicleName,
                                     String vehicleIp)
      throws IOException {
    System.out.println("ip Method" + vehicleName);

    this.ipaddress = vehicleIp;
    this.vehicleId = CommunicationAdapter.getName();

    synchronized (TCP_COMMUNICATION) {
      System.out.println("Sync Method");
      AITVTCPCommunication.connectToCommunicationAdapter(CommunicationAdapter, vehicleName);
      System.out.println("Sync Method2");

    }
    try {
      //Change the com port when needed
      TCP_COMMUNICATION.connect(this.ipaddress, this.vehicleId);
      System.out.println("Connect Method" + this.ipaddress);
    }
    catch (Exception e) {
      System.out.println("Error Unnable to connect on the port " + ipaddress);

      AITVTCPCommunication.removeCommunicationAdapter(CommunicationAdapter, vehicleName);

      throw new IOException("device error");

    }
  }

  AITVTCPCommunication getSerialCommunication() {
    return TCP_COMMUNICATION;
  }

}
