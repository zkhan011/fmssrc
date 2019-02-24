/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.strategies.basic.dispatching.phase.aitvstartupandshutdown;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.Uninterruptibles;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import static java.util.Objects.requireNonNull;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import java.util.logging.Level;
import javax.inject.Inject;
import org.opentcs.components.kernel.Router;
import org.opentcs.components.kernel.services.InternalTransportOrderService;
import org.opentcs.data.model.Vehicle;
import org.opentcs.strategies.basic.dispatching.DefaultDispatcherConfiguration;
import org.opentcs.strategies.basic.dispatching.Phase;
import org.opentcs.strategies.basic.dispatching.ProcessabilityChecker;
import org.opentcs.strategies.basic.dispatching.TransportOrderUtil;
import org.opentcs.strategies.basic.dispatching.phase.parking.ParkIdleVehiclesPhase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author zisha
 */
public class ShutDownAITVIdlePhase
    implements Phase {

  /**
   * This class's Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ParkIdleVehiclesPhase.class);
  /**
   * The transport order service.
   */
  private final InternalTransportOrderService orderService;

  /**
   * The Router instance calculating route costs.
   */
  private final Router router;
  /**
   * Checks processability of transport orders for vehicles.
   */
  private final ProcessabilityChecker processabilityChecker;

  private final TransportOrderUtil transportOrderUtil;
  /**
   * The dispatcher configuration.
   */
  private final DefaultDispatcherConfiguration configuration;
  /**
   * Indicates whether this component is initialized.
   */
  private boolean initialized;
  

  
  
 

  @Inject
  public ShutDownAITVIdlePhase(
      InternalTransportOrderService orderService,
      Router router,
      ProcessabilityChecker processabilityChecker,
      TransportOrderUtil transportOrderUtil,
      DefaultDispatcherConfiguration configuration) {
    this.router = requireNonNull(router, "router");
    this.orderService = requireNonNull(orderService, "orderService");
    this.processabilityChecker = requireNonNull(processabilityChecker, "processabilityChecker");
    this.transportOrderUtil = requireNonNull(transportOrderUtil, "transportOrderUtil");
    this.configuration = requireNonNull(configuration, "configuration");
  
  }

  @Override
  public void initialize() {
    if (isInitialized()) {
      return;
    }
    initialized = true;
  }

  @Override
  public boolean isInitialized() {
    return initialized;
  }

  @Override
  public void terminate() {
    if (!isInitialized()) {
      return;
    }

    initialized = false;
  }

  @Override
  public void run() {
    orderService.fetchObjects(Vehicle.class, this::canBeShutDown).forEach((vehicle) -> {
      new java.util.Timer().schedule(
          new java.util.TimerTask() {
            @Override
            public void run() {
              
              if (canBeShutDown(vehicle)){
                
                try {
                  calculateElapsedTime(vehicle);
                }
                catch (InterruptedException | IOException ex) {
                  java.util.logging.Logger.getLogger(ShutDownAITVIdlePhase.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              }
              
              // your code here
            }
          },
          configuration.shutdownAITVAfterThisTime()
      );
    });
  }

  private void calculateElapsedTime(Vehicle vehicle)
      throws InterruptedException, ProtocolException, IOException {
       
       
       
       // Uninterruptibles.sleepUninterruptibly(configuration.shutdownAITVAfterThisTime(), TimeUnit.MILLISECONDS);
        System.out.println(vehicle.getName());
        URL yahoo = new URL("http://192.168.0.175:5500/shutdown");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            System.out.println(inputLine);
       
        in.close();
        
   

  }

  private boolean canBeShutDown(Vehicle vehicle) {
    return vehicle.getIntegrationLevel() == Vehicle.IntegrationLevel.TO_BE_UTILIZED
        && vehicle.hasProcState(Vehicle.ProcState.IDLE)
        && vehicle.hasState(Vehicle.State.IDLE)
        && vehicle.getCurrentPosition() != null
        && vehicle.getOrderSequence() == null;
  }

}
