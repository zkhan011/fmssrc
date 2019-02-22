/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ABC
 */
import static java.util.Objects.requireNonNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Martin Grzenia (Fraunhofer IML)
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(
      propOrder = {"name", "id", "length", "energyLevelCritical", "energyLevelGood", "maxVelocity", "maxReverseVelocity",
                   "properties","vehicleIpAddress"})
public class VehicleTO
    extends PlantModelElementTO {
  
  private String vehicleIp;

  //max velocity in mm/s.
  private int maxVelocity;
  //max rev velocity in mm/s.
  private int maxReverseVelocity;
  private String type = "";
  private Long length = 0L;
  private Long energyLevelCritical = 0L;
  private Long energyLevelGood = 0L;

  @XmlAttribute
  public String getType() {
    return type;
  }

  public VehicleTO setType(String type) {
    requireNonNull(type, "type");
    this.type = type;
    return this;
  }
  
   @XmlAttribute
  public String getVehicleIpAddress() {
    return vehicleIp;
  }

  public VehicleTO setVehicleIpAddress(String vehicleIp) {
   
    this.vehicleIp = vehicleIp;
    return this;
  }
  
  
  

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public Long getLength() {
    return length;
  }

  public VehicleTO setLength(Long length) {
    requireNonNull(length, "length");
    this.length = length;
    return this;
  }

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public Long getEnergyLevelCritical() {
    return energyLevelCritical;
  }

  public VehicleTO setEnergyLevelCritical(Long energyLevelCritical) {
    requireNonNull(energyLevelCritical, "energyLevelCritical");
    this.energyLevelCritical = energyLevelCritical;
    return this;
  }

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public Long getEnergyLevelGood() {
    return energyLevelGood;
  }

  public VehicleTO setEnergyLevelGood(Long energyLevelGood) {
    requireNonNull(energyLevelGood, "energyLevelGood");
    this.energyLevelGood = energyLevelGood;
    return this;
  }

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public int getMaxVelocity() {
    return maxVelocity;
  }

  public VehicleTO setMaxVelocity(int maxVelocity) {
    this.maxVelocity = maxVelocity;
    return this;
  }

  @XmlAttribute
  @XmlSchemaType(name = "unsignedInt")
  public int getMaxReverseVelocity() {
    return maxReverseVelocity;
  }

  public VehicleTO setMaxReverseVelocity(int maxReverseVelocity) {
    this.maxReverseVelocity = maxReverseVelocity;
    return this;
  }
}