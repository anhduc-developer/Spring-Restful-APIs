package com.example.duck.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.duck.Entity.Todo;
import com.example.duck.Service.TodoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/create-todo")
    public String index() {
        Todo myTodo = new Todo("Duck", true);
        Todo newTodo = this.todoService.handleCreateTodo(myTodo);
        return "create todo with id = " + newTodo.getId();
    }

    @GetMapping("/todos")
    public String getTodos() {
        List<Todo> todos = this.todoService.handleGetTodos();
        todos.forEach(u -> {
            System.out.println(u);
        });
        return "hiihi";
    }

    @GetMapping("/update-todo")
    public String updateTodo() {
        this.todoService.handleUpdateTodo();
        return "update-todo";
    }

    @GetMapping("/delete-todo")
    public String deleteTodo() {
        this.todoService.handleDeleteTodo();
        return "hihhi";
    }
}