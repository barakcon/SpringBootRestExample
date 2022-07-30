package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreationErrorHandling() throws Exception {
        this.mockMvc.perform(post("/entities")).andDo(print()).andExpect(status().isBadRequest());
//        this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    void testEmptyDb() throws Exception {
        this.mockMvc.perform(get("/entities"))
                .andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void testCreation() throws Exception {
        String requestBody = "{\"content\": \"abcd\"}";
        this.mockMvc.perform(post("/entities")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String expected = "[{\"id\": 0, \"content\": \"abcd\"}]";

        this.mockMvc.perform(get("/entities"))
                .andExpect(status().isOk()).andExpect(content().json(expected));

    }
}
