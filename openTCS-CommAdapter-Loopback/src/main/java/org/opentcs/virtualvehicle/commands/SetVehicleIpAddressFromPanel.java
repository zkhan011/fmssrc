/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle.commands;

import org.opentcs.drivers.vehicle.AdapterCommand;
import org.opentcs.drivers.vehicle.MovementCommand;
import org.opentcs.drivers.vehicle.VehicleCommAdapter;
import org.opentcs.virtualvehicle.AGVCommAdapter;
import org.opentcs.virtualvehicle.LoopbackCommunicationAdapter;

/**
 *
 * @author zisha
 */
public class SetVehicleIpAddressFromPanel  implements AdapterCommand {
  
  
  
  /*
      String to get the desired IP address from the adpater  
  
  */
  
  private  final String vehicleIpaddress;
  
  
   /**
   * Creates a new instance.
   *
   * @param paused Whether to pause/unpause the vehicle.
   */
  public SetVehicleIpAddressFromPanel(String ip) {
    this.vehicleIpaddress = ip;
  }
  
  
  
  

   @Override
  public void execute(VehicleCommAdapter adapter) {
//   adapter.getProcessModel().setVehicleIpAddress(vehicleIpaddress);
     System.out.println("execute" + vehicleIpaddress);
     
    // adapter.getProcessModel().setVehiclePosition(vehicleIpaddress);
     
     AGVCommAdapter aitvadapter = (AGVCommAdapter) adapter;
      System.out.println("execute" + vehicleIpaddress + adapter.getProcessModel().getName());
      
     
     aitvadapter.getProcessModel().setVehicleIpAddress(vehicleIpaddress , aitvadapter);

    //LoopbackCommunicationAdapter loopbackAdapter = (LoopbackCommunicationAdapter) adapter;
   // loopbackAdapter.getProcessModel().;
  }
}
