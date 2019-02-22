/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ABC
 */
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Martin Grzenia (Fraunhofer IML)
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "id", "sourcePoint", "destinationPoint", "length", "routingCost",
                      "maxVelocity", "maxReverseVelocity", "locked", "typeMaxVelocity", 
                      "typeMaxReverseVelocity", "typeLock", "typeActions", "properties"})
public class PathTO
    extends PlantModelElementTO {

  private String sourcePoint = "";
  private String destinationPoint = "";
  private Long length = 0L;
  private Long routingCost = 0L;
  private Long maxVelocity = 0L;
  private Long maxReverseVelocity = 0L;
  private Boolean locked = false;
  private List<MaxVelocityType> typeMaxVelocity = new ArrayList<>();
  private List<MaxVelocityType> typeMaxReverseVelocity = new ArrayList<>();
  private List<LockType> typeLock = new ArrayList<>();
  private List<ActionsType> typeActions = new ArrayList<>();

  @XmlAttribute(required = true)
  public String getSourcePoint() {
    return sourcePoint;
  }

  public PathTO setSourcePoint(String sourcePoint) {
    requireNonNull(sourcePoint, "sourcePoint");
    this.sourcePoint = sourcePoint;
    return this;
  }

  @XmlAttribute(required = true)
  public String getDestinationPoint() {
    return destinationPoint;
  }

  public PathTO setDestinationPoint(String destinationPoint) {
    requireNonNull(destinationPoint, "destinationPoint");
    this.destinationPoint = destinationPoint;
    return this;
  }

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public Long getLength() {
    return length;
  }

  public PathTO setLength(Long length) {
    requireNonNull(length, "length");
    this.length = length;
    return this;
  }

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public Long getRoutingCost() {
    return routingCost;
  }

  public PathTO setRoutingCost(Long routingCost) {
    requireNonNull(routingCost, "routingCost");
    this.routingCost = routingCost;
    return this;
  }

  @XmlAttribute(required = true)
  @XmlSchemaType(name = "unsignedInt")
  public Long getMaxVelocity() {
    return maxVelocity;
  }

  public PathTO setMaxVelocity(Long maxVelocity) {
    requireNonNull(maxVelocity, "maxVelocity");
    this.maxVelocity = maxVelocity;
    return this;
  }

  @XmlAttribute(required = true)
  @XmlSchemaType(name = "unsignedInt")
  public Long getMaxReverseVelocity() {
    return maxReverseVelocity;
  }

  public PathTO setMaxReverseVelocity(Long maxReverseVelocity) {
    requireNonNull(maxReverseVelocity, "maxReverseVelocity");
    this.maxReverseVelocity = maxReverseVelocity;
    return this;
  }

  @XmlAttribute(required = true)
  public Boolean isLocked() {
    return locked;
  }

  public PathTO setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  @XmlElement
  public List<MaxVelocityType> getTypeMaxVelocity() {
    return typeMaxVelocity;
  }

  public PathTO setTypeMaxVelocity(List<MaxVelocityType> typeMaxVelocity) {
    requireNonNull(typeMaxVelocity, "typeMaxVelocity");
    this.typeMaxVelocity = typeMaxVelocity;
    return this;
  }

  @XmlElement
  public List<MaxVelocityType> getTypeMaxReverseVelocity() {
    return typeMaxReverseVelocity;
  }

  public PathTO setTypeMaxReverseVelocity(List<MaxVelocityType> typeMaxReverseVelocity) {
    requireNonNull(typeMaxReverseVelocity, "typeMaxReverseVelocity");
    this.typeMaxReverseVelocity = typeMaxReverseVelocity;
    return this;
  }

  @XmlElement
  public List<LockType> getTypeLock() {
    return typeLock;
  }

  public PathTO setTypeLock(List<LockType> typeLock) {
    requireNonNull(typeLock, "typeLock");
    this.typeLock = typeLock;
    return this;
  }

  @XmlElement
  public List<ActionsType> getTypeActions() {
    return typeActions;
  }

  public PathTO setTypeActions(List<ActionsType> typeActions) {
    requireNonNull(typeActions, "typeActions");
    this.typeActions = typeActions;
    return this;
  }

  @XmlAccessorType(XmlAccessType.PROPERTY)
  @XmlType(propOrder = {"vehicleType", "velocity"})
  public static class MaxVelocityType {

    private String vehicleType = "";
    private Long velocity = 0L;

    @XmlAttribute(required = true)
    public String getVehicleType() {
      return vehicleType;
    }

    public MaxVelocityType setVehicleType(String vehicleType) {
      requireNonNull(vehicleType, "vehicleType");
      this.vehicleType = vehicleType;
      return this;
    }

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "unsignedInt")
    public Long getVelocity() {
      return velocity;
    }

    public MaxVelocityType setVelocity(Long velocity) {
      requireNonNull(velocity, "velocity");
      this.velocity = velocity;
      return this;
    }
  }

  @XmlAccessorType(XmlAccessType.PROPERTY)
  @XmlType(propOrder = {"vehicleType", "locked"})
  public static class LockType {

    private String vehicleType = "";
    private Boolean locked = false;

    @XmlAttribute(required = true)
    public String getVehicleType() {
      return vehicleType;
    }

    public LockType setVehicleType(String vehicleType) {
      requireNonNull(vehicleType, "vehicleType");
      this.vehicleType = vehicleType;
      return this;
    }

    @XmlAttribute(required = true)
    public Boolean isLocked() {
      return locked;
    }

    public LockType setLocked(Boolean locked) {
      this.locked = locked;
      return this;
    }
  }

  @XmlAccessorType(XmlAccessType.PROPERTY)
  @XmlType(propOrder = {"vehicleType", "actions"})
  public static class ActionsType {

    private String vehicleType = "";
    private List<Action> actions = new ArrayList<>();

    @XmlAttribute(required = true)
    public String getVehicleType() {
      return vehicleType;
    }

    public ActionsType setVehicleType(String vehicleType) {
      requireNonNull(vehicleType, "vehicleType");
      this.vehicleType = vehicleType;
      return this;
    }

    @XmlElement(name = "action", required = true)
    public List<Action> getActions() {
      return actions;
    }

    public ActionsType setActions(List<Action> actions) {
      requireNonNull(actions, "actions");
      this.actions = actions;
      return this;
    }

    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(propOrder = {"distance", "action", "orientations"})
    public static class Action {

      private Long distance = 0L;
      private String action = "";
      private List<Orientation> orientations = new ArrayList<>();

      @XmlAttribute(required = true)
      @XmlSchemaType(name = "unsignedInt")
      public Long getDistance() {
        return distance;
      }

      public Action setDistance(Long distance) {
        requireNonNull(distance, "distance");
        this.distance = distance;
        return this;
      }

      @XmlAttribute(required = true)
      public String getAction() {
        return action;
      }

      public Action setAction(String action) {
        requireNonNull(action, "action");
        this.action = action;
        return this;
      }

      @XmlElement(name = "orientation", required = true)
      public List<Orientation> getOrientations() {
        return orientations;
      }

      public Action setOrientations(List<Orientation> orientations) {
        requireNonNull(orientations, "orientations");
        this.orientations = orientations;
        return this;
      }
    }

    @XmlAccessorType(XmlAccessType.PROPERTY)
    public static class Orientation {

      private String name = "";

      @XmlAttribute(required = true)
      public String getName() {
        return name;
      }

      public Orientation setName(String name) {
        requireNonNull(name, "name");
        this.name = name;
        return this;
      }
    }
  }
}

