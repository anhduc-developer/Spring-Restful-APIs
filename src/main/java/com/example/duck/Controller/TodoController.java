package com.example.duck.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.duck.Entity.Todo;
import com.example.duck.Service.TodoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo newTodo = this.todoService.handleCreateTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos() {
        List<Todo> todos = this.todoService.handleGetTodos();
        return ResponseEntity.ok().body(todos);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody Todo newtodo) {
        this.todoService.handleUpdateTodo(id, newtodo);
        return ResponseEntity.ok().body("update succeed!");
    }

    @RequestMapping(value = "/duck", method = RequestMethod.GET)
    public String requestGetDuck() {
        return "Welcome to Spring Boot!";
    }

    @GetMapping("/admin/duck")
    public ResponseEntity<Todo> getTestTodo() {
        Todo newTodo = new Todo("Tomorrow Duck", false);
        return ResponseEntity.ok().body(newTodo);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        // return "Welcome to Spring Boot !";
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo currentTodo = this.todoService.getTodoById(id);
        return ResponseEntity.ok().body(currentTodo);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        this.todoService.handleDeleteTodo(id);
        return ResponseEntity.ok().body("Delete Succeed!");
    }
}