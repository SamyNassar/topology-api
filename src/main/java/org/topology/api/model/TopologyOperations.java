package org.topology.api.model;

import java.util.List;

public interface TopologyOperations {

    void addComponent(String component);

    int removeComponent(String id);

    List queryDevices();

}
