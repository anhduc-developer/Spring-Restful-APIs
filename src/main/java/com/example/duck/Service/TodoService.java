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

    public Todo handleCreateTodo(Todo todo) {
        Todo createdTodo = this.todoRepository.save(todo);
        return createdTodo;
    }

    public List<Todo> handleGetTodos() {
        List<Todo> todos = this.todoRepository.findAll();
        return todos;
    }

    public void handleUpdateTodo() {
        Optional<Todo> todoOptional = this.todoRepository.findById(1L);
        if (todoOptional.isPresent()) {
            Todo currentTodo = todoOptional.get();
            currentTodo.setCompleted(false);
            currentTodo.setUsername("@nhDuck");
            this.todoRepository.save(currentTodo);
        }
    }

    public void handleDeleteTodo() {
        this.todoRepository.deleteById(1L);
    }
}
