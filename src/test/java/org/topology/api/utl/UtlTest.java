package org.topology.api.utl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtlTest {

    @Test
    void readJSON() {
        assertThrows(RuntimeException.class, () -> {
            Utl.readJSON("here.json");
        });
    }
}