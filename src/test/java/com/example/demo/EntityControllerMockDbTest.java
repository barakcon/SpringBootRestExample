package com.example.demo;

import com.example.demo.fetchers.Database;
import com.example.demo.models.Entity;
import com.example.demo.models.EntityToCreate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EntityControllerMockDbTest {

    @TestConfiguration
    static class AdditionalConfig {
        @Primary
        @Bean
        Database<Entity, EntityToCreate> db() {
            return new Database<Entity, EntityToCreate>() {
                @Override
                public Entity create(EntityToCreate input) {
                    return new Entity(1, input.getContent());
                }

                @Override
                public List<Entity> getAll() {
                    return Collections.emptyList();
                }

                @Override
                public Entity get(Long id) {
                    return null;
                }
            };
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testEmptyDb() throws Exception {
        this.mockMvc.perform(get("/entities"))
                .andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void testCreation() throws Exception {
        String requestBody = "{\"content\": \"abcd\"}";
        this.mockMvc.perform(post("/entities"));

        this.mockMvc.perform(get("/entities"))
                .andExpect(status().isOk()).andExpect(content().json("[]"));

    }
}
