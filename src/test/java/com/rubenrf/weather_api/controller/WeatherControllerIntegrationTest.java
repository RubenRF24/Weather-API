package com.rubenrf.weather_api.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTodayWeather() throws Exception {
        String city = "Bogota";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/{city}", city))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.days", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resolvedAddress").value("Bogotá, D.C., Colombia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude").value(4.61496))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude").value(-74.0694));
    }

    @Test
    public void testInvalidatedCity() throws Exception {
        String city = "Bog";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/{city}", city))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        Matchers.containsString("Bad API Request:Invalid location parameter value.")));
    }

    @Test
    public void testInvalidatedEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/invalidated-endpoint"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",
                        Matchers.containsString("No static resource api/invalidated-endpoint.")));
    }

}
