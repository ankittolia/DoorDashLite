package doordash.com.doordash.service.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantModelTest {

    @Test
    public void testRestaurantModel() {
        String name = "Test Restaurant";
        String description = "A test description";
        String status = "open";
        String coverImgUrl = "http://example.com/img.png";

        RestaurantModel restaurant = new RestaurantModel(name, description, status, coverImgUrl);
        assertEquals(name, restaurant.getName());
        assertEquals(description, restaurant.getDescription());
        assertEquals(status, restaurant.getStatus());
        assertEquals(coverImgUrl, restaurant.getCoverImgUrl());

        // Test setters
        restaurant.setName("New Name");
        restaurant.setDescription("New Description");
        restaurant.setStatus("closed");
        restaurant.setCoverImgUrl("http://example.com/new.png");

        assertEquals("New Name", restaurant.getName());
        assertEquals("New Description", restaurant.getDescription());
        assertEquals("closed", restaurant.getStatus());
        assertEquals("http://example.com/new.png", restaurant.getCoverImgUrl());
    }
}
