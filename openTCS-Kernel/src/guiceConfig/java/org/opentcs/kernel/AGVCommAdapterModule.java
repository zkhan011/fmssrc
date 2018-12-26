/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.kernel;

import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.opentcs.customizations.kernel.KernelInjectionModule;
import org.opentcs.virtualvehicle.AGVAdapterComponentFactory;
import org.opentcs.virtualvehicle.AGVCommAdapterFactory;
import org.opentcs.virtualvehicle.AITVVehicleConfiguration;
import org.opentcs.virtualvehicle.LoopbackAdapterComponentsFactory;
import org.opentcs.virtualvehicle.LoopbackCommunicationAdapterFactory;
import org.opentcs.virtualvehicle.VirtualVehicleConfiguration;

/**
 *
 * @author zisha
 */
public class AGVCommAdapterModule extends KernelInjectionModule {

  // tag::documentation_createCommAdapterModule[]
  @Override
  protected void configure() {
    configureLoopbackAdapterDependencies();
    vehicleCommAdaptersBinder().addBinding().to(AGVCommAdapterFactory.class);
  }
  // end::documentation_createCommAdapterModule[]

  private void configureLoopbackAdapterDependencies() {
    install(new FactoryModuleBuilder().build(AGVAdapterComponentFactory.class));

    bind(AITVVehicleConfiguration.class)
        .toInstance(getConfigBindingProvider().get(AITVVehicleConfiguration.PREFIX,
                                                   AITVVehicleConfiguration.class));
  }
}
