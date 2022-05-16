package org.topology.api.model.utl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.topology.api.model.Topology;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Utl {

    public static Topology readJSON(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(Paths.get(path).toFile(), Topology.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeJSON(String path, Topology topology) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(path), topology);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
