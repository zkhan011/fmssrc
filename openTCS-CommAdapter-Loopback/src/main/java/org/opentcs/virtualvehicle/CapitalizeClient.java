package org.opentcs.virtualvehicle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A simple Swing-based client for the capitalization server.
 * It has a main frame window with a text field for entering
 * strings and a textarea to see the results of capitalizing
 * them.
 */
public class CapitalizeClient
    extends Thread {

  private boolean ct = false;

  private String ipaddress;

  private Socket sock;

  // reading from keyboard (keyRead object)
  private BufferedReader keyRead;
  // sending to client (pwrite object)
  private OutputStream ostream;

  private PrintWriter pwrite;

  // receiving from server ( receiveRead  object)
  private InputStream istream;
  protected DataInputStream dataInputStream;  // InputStream to receive data
  private BufferedReader receiveRead;
  private String currenposition;

  public CapitalizeClient(String ip)
      throws IOException {

    this.ipaddress = ip;
    this.sock = new Socket(ipaddress, 11000);
    this.keyRead = new BufferedReader(new InputStreamReader(System.in));
    this.ostream = this.sock.getOutputStream();
    this.pwrite = new PrintWriter(ostream, true);
    this.istream = this.sock.getInputStream();
    this.dataInputStream = new DataInputStream(istream);
    this.receiveRead = new BufferedReader(new InputStreamReader(istream));

  }

  @Override
  public void run() {

    while (!ct) {

    }

  }

  public void disconnect()
      throws IOException {

    System.out.println("org.opentcs.virtualvehicle.CapitalizeClient.disconnect()");
    try {
      this.ct = true;

    }
    finally {
      this.istream.close();
      this.ostream.close();
      this.pwrite.close();
      this.sock.close();
    }

  }

  public void sendMessage(String message) {

    System.out.println("org.opentcs.virtualvehicle.CapitalizeClient.send");
    this.pwrite.println(message);

  }

  public String getPosition()
      throws IOException {

    this.pwrite.println("<EOF>");

    byte[] bytedata = new byte[1024];
    int character;
    StringBuilder data = new StringBuilder();

    while ((character = istream.read()) != -1) {

      if ((char) character == '<') {

        if ((char) istream.read() == 'E') {

          if ((char) istream.read() == 'O') {

            if ((char) istream.read() == 'F') {

              if ((char) istream.read() == '>') {

                break;

              }

            }

          }

        }

      }
      data.append((char) character);

    }

    System.out.println("This is received : " + ":::::::::" + data.toString());

    String position = data.toString();
    this.pwrite.flush();

    return position;

  }

  public Boolean readCommand()
      throws IOException {

    BufferedReader reader = this.receiveRead;

    if (reader.readLine() != null) {

      System.out.println(reader.readLine().toLowerCase());
      if (reader.readLine().toLowerCase() == "yes") {

        return true;

      }
    }

    return false;

  }

  public static void main(String[] args)
      throws Exception {
    System.out.println("The capitalization Client  is running.");

    CapitalizeClient c = new CapitalizeClient("127.0.0.1");

    c.start();
    c.getPosition();

  }

}
