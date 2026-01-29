package com.example.duck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.duck.Entity.Todo;
import com.example.duck.Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HelloController {
    @Autowired
    private ObjectMapper om;

    @GetMapping("/")
    public ResponseEntity<String> index() throws Exception {
        // tu json => object: (frontend send data to backend)
        String json = """
                {
                "id": 11,
                "name" : "ducptit",
                "email": "ducptit@gmail.com"
                }
                """;
        User test = om.readValue(json, User.class);
        User duck = new User(11L, "duckk", "duckk@gmail.com");
        String duckJson = om.writeValueAsString(duck);
        return ResponseEntity.ok().body(duckJson);
    }

    @GetMapping("/duckuro")
    public ResponseEntity<Todo> testTodo() {
        Todo todo = new Todo("Todo", true);
        return ResponseEntity.ok().body(todo);
    }
}