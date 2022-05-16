package org.topology.api.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Topology implements TopologyOperations {
    private String id;
    private List<LinkedHashMap> components = new ArrayList<>();

    public Topology() {
    }


    public Topology(String id) {
        this.id = id;
    }

    public Topology(String id, List<LinkedHashMap> components) {
        this.id = id;
        this.components = components;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LinkedHashMap> getComponents() {
        return this.components;
    }

    public void setComponents(List<LinkedHashMap> components) {
        this.components = components;
    }


    @Override
    public String toString() {
        return "Topology{" +
                "id:'" + id + '\'' +
                ", components:" + components +
                '}';
    }


    @Override
    public void addComponent(String component) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(component, Map.class);
            this.components.add((LinkedHashMap) map);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int removeComponent(String id) {
        for (int i = 0; i < this.components.size(); i++) {
            LinkedHashMap component = this.components.get(i);
            if (component.get("id").equals(id)) {
                this.components.remove(i);
                return 1;
            }
        }
        return -1;
    }

    @Override
    public List<String> queryDevices() {
        List<LinkedHashMap> components = this.getComponents();
        List<String> devices = new LinkedList<>();

        for (LinkedHashMap component : components) {
            devices.add((String) component.get("type"));
        }
        return devices;
    }

}
