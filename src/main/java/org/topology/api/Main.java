package org.topology.api;

import org.topology.api.model.Topology;
import org.topology.api.utl.Utl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Topology topology;


        // Read topology from JSON file.
        topology = Utl.readJSON("src/main/java/org/topology/api/topology.json");
        System.out.println("\ntopology with id (" + topology.getId() + ") is : \n" + topology.toString());


        //Add new component in topology.
        String topologyJSON = "{\"type\":\"capacitors\",\"id\":\"cap1\",\"specs\":{\"default\":5,\"min\":1,\"max\":10},\"netlist\":{\"c1\":\"hdd\",\"c2\":\"ssd\",\"c3\":\"ram\"}}";
        topology.addComponent(topologyJSON);
        System.out.println("\nThe new topology with id (" + topology.getId() + ") after adding component is : \n" + topology.toString());


        //Remove component from topology.
        topology.removeComponent("res1");
        System.out.println("\nThe new topology with id (" + topology.getId() + ") after deleting component is : \n" + topology.toString());


        // Write topology in a JSON file.
        Utl.writeJSON("src/main/java/org/topology/api/written-topology.json", topology);


        // Query Devices in the Topology.
        List<String> devices = topology.queryDevices();
        System.out.println("\nDevices list in the topology with id (" + topology.getId() + ") is : " + devices);


    }
}