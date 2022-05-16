package org.topology.api.model;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopologyTest {

    @Test
    void addComponent() {

        var topology = new Topology("top1");
        String compJSON = "{\"type\":\"pc\",\"id\":\"pc1\"}";
        topology.addComponent(compJSON);

        assertEquals("top1", topology.getId());
        assertEquals("pc1", topology.getComponents().get(0).get(("id")));
        assertEquals("pc", topology.getComponents().get(0).get(("type")));


        final var topology2 = new Topology();
        topology2.addComponent(compJSON);
        assertEquals("pc1", topology2.getComponents().get(0).get(("id")));
        assertThrows(AssertionFailedError.class, () -> {
            assertEquals("top", topology2.getId());
        });

    }

    @Test
    void removeComponent() {
        List<LinkedHashMap> compJSON = new ArrayList<>();
        var m1 = new LinkedHashMap();
        var m2 = new LinkedHashMap();

        m1.put("type", "laptop");
        m1.put("id", "pc0");
        m2.put("type", "desktop");
        m2.put("id", "pc1");

        compJSON.add(m1);
        compJSON.add(m2);

        var topology = new Topology("top1", compJSON);
        assertEquals("pc0", topology.getComponents().get(0).get("id"));
        assertEquals("desktop", topology.getComponents().get(1).get("type"));

        topology.removeComponent("pc0");
        assertEquals("pc1", topology.getComponents().get(0).get("id"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            topology.getComponents().get(1).get("id");
        });
    }

    @Test
    void queryDevices() {

        List<LinkedHashMap> compJSON = new ArrayList<>();
        var m1 = new LinkedHashMap();
        var m2 = new LinkedHashMap();

        m1.put("type", "laptop");
        m1.put("id", "pc0");
        m2.put("type", "desktop");
        m2.put("id", "pc1");

        compJSON.add(m1);
        compJSON.add(m2);

        var topology = new Topology("top1", compJSON);
        assertArrayEquals(new String[]{"laptop", "desktop"}, topology.queryDevices().toArray(new String[0]));
    }
}