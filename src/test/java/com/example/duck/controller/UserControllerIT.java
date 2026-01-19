package com.example.duck.controller;

import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.example.duck.Entity.User;
import com.example.duck.Repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser_shouldReturnUser_whenValid() throws Exception {
        // arrange
        User inputUser = new User(1L, "duckkIT55", "duckIT581@gmail.com");
        // action
        String resultStr = mockMvc.perform(
                post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(inputUser)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        // assert
        System.out.println("resultStr = " + resultStr);
        User outputUser = objectMapper.readValue(resultStr, User.class);
        assertEquals(inputUser.getName(), outputUser.getName());
    }

    @Test
    public void getUsers_shouldReturnAllUsers() throws Exception {
        // arrange
        this.userRepository.deleteAll(); // xoa tat ca db de test chinh xac nhat
        User user1 = new User(null, "name1", "name1@gmail.com");
        User user2 = new User(null, "name2", "name2@gmail.com");
        List<User> data = List.of(user1, user2);
        this.userRepository.saveAll(data);
        // action
        String resultStr = this.mockMvc.perform(
                get("/users")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<User> result = objectMapper.readValue(resultStr, new TypeReference<List<User>>() {

        });
        // assert
        assertEquals(2, result.size());
        assertEquals("name1@gmail.com", result.get(0).getEmail());
    }

    @Test
    public void getUserById_shouldReturnUser_whenUserExist() throws Exception {
        // arrange
        this.userRepository.deleteAll();
        User user = new User(null, "duckuroo", "ducktest@gmai.com");
        User userInput = this.userRepository.saveAndFlush(user);

        // action
        String resultStr = this.mockMvc.perform(get("/users/{id}", userInput.getId())).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        User outputUser = objectMapper.readValue(resultStr, User.class);
        // assert
        assertEquals("duckuroo", outputUser.getName());
    }

    @Test
    public void getUserById_shouldEmpty_whenIdNotFound() throws Exception {

        this.userRepository.deleteAll();
        User user = new User(1L, "duckkk", "duckkk@gmail.com");

        this.mockMvc.perform(get("/users/{id}", 0)).andExpect(status().isNotFound());
    }

    @Test
    public void updateUser_shouldReturnUser_whenUserExist() throws Exception {
        this.userRepository.deleteAll();
        User user = new User(1L, "duckkk", "duckkk@gmail.com");
        User userInput = this.userRepository.saveAndFlush(user);
        User updateUser = new User(userInput.getId(), "new-duckkk", "new-duckkk@gmail.com");
        String resultStr = this.mockMvc
                .perform(put("/users/{id}", userInput.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateUser)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        User result = this.objectMapper.readValue(resultStr, User.class);
        assertEquals("new-duckkk", result.getName());
    }

    @Test
    public void deleteUserById_shoudReturnVoid_whenUserExist() throws Exception {
        this.userRepository.deleteAll();
        User user = new User(1L, "duckkk", "duckkk@gmail.com");
        User inputUser = this.userRepository.saveAndFlush(user);
        this.mockMvc.perform(delete("/users/{id}", inputUser.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Long countDB = this.userRepository.count();
        assertEquals(0L, countDB);
    }
}
