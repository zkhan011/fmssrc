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
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Martin Grzenia (Fraunhofer IML)
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "id", "xPosition", "yPosition", "zPosition",
                      "vehicleOrientationAngle", "type", "outgoingPaths", "properties"})
public class PointTO
    extends PlantModelElementTO {
  
  private Long xPosition = 0L;
  private Long yPosition = 0L;
  private Long zPosition = 0L;
  private Float vehicleOrientationAngle = 0.0F;
  private String type = "";
  private List<OutgoingPath> outgoingPaths = new ArrayList<>();

  @XmlAttribute(required = true)
  public Long getxPosition() {
    return xPosition;
  }

  public PointTO setxPosition(Long xPosition) {
    requireNonNull(xPosition, "xPosition");
    this.xPosition = xPosition;
    return this;
  }

  @XmlAttribute(required = true)
  public Long getyPosition() {
    return yPosition;
  }

  public PointTO setyPosition(Long yPosition) {
    requireNonNull(yPosition, "yPosition");
    this.yPosition = yPosition;
    return this;
  }

  @XmlAttribute
  public Long getzPosition() {
    return zPosition;
  }

  public PointTO setzPosition(Long zPosition) {
    requireNonNull(zPosition, "zPosition");
    this.zPosition = zPosition;
    return this;
  }

  @XmlAttribute
  public Float getVehicleOrientationAngle() {
    return vehicleOrientationAngle;
  }

  public PointTO setVehicleOrientationAngle(Float vehicleOrientationAngle) {
    requireNonNull(vehicleOrientationAngle, "vehicleOrientationAngle");
    this.vehicleOrientationAngle = vehicleOrientationAngle;
    return this;
  }

  @XmlAttribute(required = true)
  public String getType() {
    return type;
  }

  public PointTO setType(String type) {
    requireNonNull(type, "type");
    this.type = type;
    return this;
  }

  @XmlElement(name = "outgoingPath")
  public List<OutgoingPath> getOutgoingPaths() {
    return outgoingPaths;
  }

  public PointTO setOutgoingPaths(List<OutgoingPath> outgoingPath) {
    requireNonNull(outgoingPath, "outgoingPath");
    this.outgoingPaths = outgoingPath;
    return this;
  }

  @XmlAccessorType(XmlAccessType.PROPERTY)
  public static class OutgoingPath {

    private String name = "";

    @XmlAttribute(required = true)
    public String getName() {
      return name;
    }

    public OutgoingPath setName(String name) {
      requireNonNull(name, "name");
      this.name = name;
      return this;
    }
  }
}