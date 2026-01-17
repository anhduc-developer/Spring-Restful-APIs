// package com.example.duck.Controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.duck.Entity.Todo;
// import com.example.duck.Service.TodoService;

// @RestController
// public class TestController {
// private final TodoService todoService;

// public TestController(TodoService todoService) {
// this.todoService = todoService;
// }

// @GetMapping("/todos/{id}")
// public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
// Todo todo = this.todoService.handleGetTodo(id);
// return ResponseEntity.ok().body(todo);
// }

// @GetMapping("/todos")
// public ResponseEntity<List<Todo>> getAllTodos() {
// List<Todo> todos = this.todoService.handleGetTodos();
// return ResponseEntity.ok().body(todos);
// }

// @PostMapping("/todos")
// public ResponseEntity<String> createTodo(@RequestBody Todo todo) {
// this.todoService.handlecreateTodo(todo);
// return ResponseEntity.status(HttpStatus.CREATED).body("Create a Todo
// Succeed!");
// }

// @PutMapping("/todos/{id}")
// public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody
// Todo todo) {
// this.todoService.handelUpdateTodo(id, todo);
// return ResponseEntity.ok().body("Update a Todo succeed !");
// }

// @DeleteMapping("/delete/{id}")
// public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
// this.todoService.handleDeleteTodo(id);
// return ResponseEntity.ok().body("Delete Todo with id = " + id + "Succeed!");
// }
// }
