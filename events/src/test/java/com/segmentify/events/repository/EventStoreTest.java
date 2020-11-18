package com.segmentify.events.repository;

import com.segmentify.events.model.dao.ProductViewPostedEvent;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class EventStoreTest {
    @MockBean
    private EventStore eventStore;

    @Test
    public void productViewAddEvent() {
        ProductViewPostedEvent event = new ProductViewPostedEvent();
        event.setUserId("xxxx");
        event.setName("PRODUCT_VIEW");

        eventStore = EventStore.getInstance();

        eventStore.addEvent("xxxx", event);
    }

    @Test
    public void pageViewAddEvent() {
        ProductViewPostedEvent event = new ProductViewPostedEvent();
        event.setUserId("xxxx");
        event.setName("PAGE_VIEW");

        eventStore = EventStore.getInstance();

        eventStore.addEvent("xxxx", event);
    }
}
