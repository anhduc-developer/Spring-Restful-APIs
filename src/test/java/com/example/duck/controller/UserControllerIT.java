package com.example.duck.controller;

import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.duck.IntegrationTest;
import com.example.duck.Entity.User;
import com.example.duck.Repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@IntegrationTest // define annotation
@AutoConfigureMockMvc
@Transactional // spring-framework
public class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach // luon luon chay truoc vi neu dung after co the bi fail => after ko chay den
    public void init() {

    }

    @Test
    public void createUser_shouldReturnUser_whenValid() throws Exception {
        User inputUser = new User(null, "anhDuckIT", "anhDuckIT@gmail.com");
        String resultString = this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(inputUser))).andExpect(status().isCreated()).andReturn()
                .getResponse().getContentAsString();
        User outputUser = this.objectMapper.readValue(resultString, User.class);
        assertEquals("anhDuckIT", outputUser.getName());
    }

    @Test
    public void getUsers_shouldReturnUsers() throws Exception {
        User user1 = new User(null, "name1", "name1@gmail.com");
        User user2 = new User(null, "name2", "name2@gmail.com");
        List<User> users = List.of(user1, user2);
        this.userRepository.saveAll(users);
        String resultString = this.mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        List<User> result = this.objectMapper.readValue(resultString, new TypeReference<List<User>>() {
        });
        assertEquals(2, result.size());
        assertEquals("name1", result.get(0).getName());
    }

    @Test
    public void getUserById_shouldReturnUser_whenUserIdValid() throws Exception {
        User user = new User(null, "duckuro", "duckuro@gmail.com");
        User inputUser = this.userRepository.saveAndFlush(user);
        String resultString = this.mockMvc.perform(get("/users/{id}",
                inputUser.getId())).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        User outputUser = this.objectMapper.readValue(resultString, User.class);
        assertEquals("duckuro", outputUser.getName());
        assertEquals("duckuro@gmail.com", outputUser.getEmail());
    }

    @Test
    public void getUserById_shouldEmpty_whenIdNotFound() throws Exception {

        User user = new User(1L, "duckkk", "duckkk@gmail.com");

        this.mockMvc.perform(get("/users/{id}", 0)).andExpect(status().isNotFound());
    }

    @Test
    public void updateUser_shouldReturnUser_whenUserExist() throws Exception {
        User user = new User(1L, "duckkk", "duckkk@gmail.com");
        User userInput = this.userRepository.saveAndFlush(user);
        User updateUser = new User(userInput.getId(), "new-duckkk",
                "new-duckkk@gmail.com");
        String resultStr = this.mockMvc
                .perform(put("/users/{id}",
                        userInput.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updateUser)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        User result = this.objectMapper.readValue(resultStr, User.class);
        assertEquals("new-duckkk", result.getName());
    }

    @Test
    public void deleteUserById_shouldReturnVoid_whenUserExists() throws Exception {
        User user = new User(1L, "delete", "delete@gmail.com");
        User inputUser = this.userRepository.saveAndFlush(user);
        this.mockMvc.perform(delete("/users/{id}",
                inputUser.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Long countDB = this.userRepository.count();
        assertEquals(0, countDB);
    }

}
