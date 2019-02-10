package org.opentcs.virtualvehicle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.opentcs.data.model.Triple;
import static org.opentcs.virtualvehicle.AITVTCPCommunication.getAGVCommAdapterName;

/**
 *
 * @author zisha
 * This is UDP Server to get the real-time information of the vehicle.
 */
public class UDPpositionalserver
    extends Thread {

  private DatagramSocket socket;
  private boolean running;
  private byte[] buf = new byte[1024];
  private AGVCommAdapter myvehicleadapter;
  private String receivedData;
  private String[] namesAndPositionData;
  private Triple position;
  private Double angle = 90.0;

  public UDPpositionalserver()
      throws SocketException {
    socket = new DatagramSocket(4445);
  }

  public void run() {
    running = true;

    while (running) {
      DatagramPacket packet
          = new DatagramPacket(buf, buf.length);
      try {
        socket.receive(packet);
      }
      catch (IOException ex) {
        Logger.getLogger(UDPpositionalserver.class.getName()).log(Level.SEVERE, null, ex);
      }

      InetAddress address = packet.getAddress();
      int port = packet.getPort();
      packet = new DatagramPacket(buf, buf.length, address, port);
      String received
          = new String(packet.getData(), 0, packet.getLength());
      System.out.println(received);
      receivedData = received;

      namesAndPositionData = receivedData.split(",");
      // filter the received data in its name and positional data comming from the UDP client.

      String vehiclename = namesAndPositionData[0];

      System.out.println(namesAndPositionData[0] + "," + namesAndPositionData[1] + "," + namesAndPositionData[2] + "," + namesAndPositionData[3]);

      if (namesAndPositionData[1] != null && namesAndPositionData[1] != null && namesAndPositionData[1] != null) {
        long x = Long.parseLong(namesAndPositionData[1]);
        long y = Long.parseLong(namesAndPositionData[2]);
        long z = Long.parseLong(namesAndPositionData[3]);

        angle = Double.parseDouble(namesAndPositionData[4]);
        // assigning positional data
        position = new Triple(x, y, z);
      }

      if (vehiclename != null && position != null) {
        // getting adapter for the received vehicle data fro string
        myvehicleadapter = getAGVCommAdapterName(vehiclename);
        if (myvehicleadapter != null) {
          myvehicleadapter.getProcessModel().setVehiclePrecisePosition(position);
          myvehicleadapter.getProcessModel().setVehicleOrientationAngle(angle);

          // myvehicleadapter.getProcessModel().setVehiclePosition(position);
        }
      }

      namesAndPositionData = null;

      if (received.equals("end")) {
        running = false;
        continue;
      }
      try {
        socket.send(packet);
      }
      catch (IOException ex) {
        Logger.getLogger(UDPpositionalserver.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    socket.close();
  }

  public static void main(String args[])
      throws SocketException {

    new UDPpositionalserver().start();

  }

}
