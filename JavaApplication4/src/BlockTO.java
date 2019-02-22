/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Martin Grzenia (Fraunhofer IML)
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "id", "members", "properties"})
public class BlockTO
    extends PlantModelElementTO {

  private List<MemberTO> members = new ArrayList<>();
  
  @XmlElement(name = "member")
  public List<MemberTO> getMembers() {
    return members;
  }

  public BlockTO setMembers(List<MemberTO> members) {
    requireNonNull(members, "members");
    this.members = members;
    return this;
  }
}
