package com.example.ecom.inventory;

import com.example.ecom.common.event.OrderCreatedEvent;
import com.example.ecom.inventory.listener.InventoryListener;
import com.example.ecom.inventory.model.Inventory;
import com.example.ecom.inventory.repository.InventoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceIntegrationTest {

    @Autowired
    private InventoryListener inventoryListener;

    @MockBean
    private InventoryRepository inventoryRepository;

    @Test
    public void testHandleOrderCreated() {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId("order-123");
        OrderCreatedEvent.OrderItemDto item = new OrderCreatedEvent.OrderItemDto("prod-1", 2);
        event.setItems(Collections.singletonList(item));

        Inventory inventory = new Inventory();
        inventory.setProductId("prod-1");
        inventory.setQuantity(10);

        when(inventoryRepository.findByProductId("prod-1")).thenReturn(Optional.of(inventory));

        inventoryListener.handleOrderCreated(event);

        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }
}
