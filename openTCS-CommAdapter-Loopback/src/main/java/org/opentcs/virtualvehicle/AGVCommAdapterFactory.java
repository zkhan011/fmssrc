/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.virtualvehicle;

import static java.util.Objects.requireNonNull;
import java.util.ResourceBundle;
import javax.inject.Inject;
import org.opentcs.data.model.Vehicle;
import org.opentcs.drivers.vehicle.VehicleCommAdapter;
import org.opentcs.drivers.vehicle.VehicleCommAdapterDescription;
import org.opentcs.drivers.vehicle.VehicleCommAdapterFactory;

/**
 * A factory for AITV Communication Adapter (AITV Vehicles)
 * @author zishan
 */
public class AGVCommAdapterFactory implements VehicleCommAdapterFactory {
  
  /**
   * The adapter components factory.
   */
  private final AGVAdapterComponentFactory adapterFactory;
  /**
   * Indicates whether this component is initialized or not.
   */
  private boolean initialized;

  /**
   * Creates a new factory.
   *
   * @param componentsFactory The adapter components factory.
   */
  
  @Inject
  public AGVCommAdapterFactory(AGVAdapterComponentFactory componentsFactory) {
    this.adapterFactory = requireNonNull(componentsFactory, "componentsFactory");
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
  public String getAdapterDescription() {
    return ResourceBundle.getBundle("org/opentcs/aitvvehicle/Bundle")
        .getString("AdapterFactoryDescription");
  }

  @Override
  public VehicleCommAdapterDescription getDescription() {
    return new LoopbackCommunicationAdapterDescription();
  }

  @Override
  public boolean providesAdapterFor(Vehicle vehicle) {
    requireNonNull(vehicle, "vehicle");
    return true;
  }

  @Override
  public AGVCommAdapter getAdapterFor(Vehicle vehicle) {
    requireNonNull(vehicle, "vehicle");
    return adapterFactory.createAGVCommAdapter(vehicle);
  }
  
}