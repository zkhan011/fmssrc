/*
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.strategies.basic.routing.jgrapht;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.alg.shortestpath.BidirectionalDijkstraShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.opentcs.components.kernel.services.TCSObjectService;

/**
 *
 * @author zisha
 */
public class AstarPointRouterFactory extends AbstractPointRouterFactory {

  /**
   * Creates a new instance.
   *
   * @param objectService The object service providing model data.
   * @param mapper Maps the plant model to a graph.
   */
  @Inject
  public AstarPointRouterFactory(@Nonnull TCSObjectService objectService,
                                    @Nonnull ModelGraphMapper mapper) {
    super(objectService, mapper);
  }

  @Override
  protected ShortestPathAlgorithm<String, ModelEdge> createShortestPathAlgorithm(
      Graph<String, ModelEdge> graph) {
    
   return new DijkstraShortestPath<>(graph);
  // return new AStarShortestPath<>(graph);

  }

}
