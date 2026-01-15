package com.example.ecom.inventory.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class InventoryTest {

    @Test
    public void testInventory() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProductId("prod-1");
        inventory.setQuantity(100);

        assertEquals(Long.valueOf(1L), inventory.getId());
        assertEquals("prod-1", inventory.getProductId());
        assertEquals(Integer.valueOf(100), inventory.getQuantity());
    }
}
