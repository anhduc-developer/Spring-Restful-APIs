package com.example.duck.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.duck.Entity.Todo;
import com.example.duck.Repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void handlecreateTodo(Todo todo) {
        this.todoRepository.save(todo);
    }

    public List<Todo> handleGetTodos() {
        List<Todo> todos = this.todoRepository.findAll();
        return todos;
    }

    public Todo handleGetTodo(Long id) {
        Optional<Todo> optionalTodo = this.todoRepository.findById(id);
        return optionalTodo.isPresent() ? optionalTodo.get() : null;
    }

    public Todo handelUpdateTodo(Long id, Todo todo) {
        Optional<Todo> optionalTodo = this.todoRepository.findById(id);
        Todo newTodo = new Todo();
        if (optionalTodo.isPresent()) {
            Todo oldTodo = optionalTodo.get();
            newTodo.setCompleted(todo.isCompleted());
            newTodo.setUsername(todo.getUsername());
            newTodo.setId(id);
        }
        return this.todoRepository.save(newTodo);
    }

    public void handleDeleteTodo(Long id) {
        this.todoRepository.deleteById(id);
    }
}
