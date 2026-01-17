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

    public void handleUpdateTodo(Long id, Todo newTodo) {
        Optional<Todo> todoOptional = this.todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo currentTodo = todoOptional.get();
            currentTodo.setCompleted(newTodo.isCompleted());
            currentTodo.setUsername(newTodo.getUsername());
            this.todoRepository.save(currentTodo);
        }
    }

    public void handleDeleteTodo(Long id) {
        this.todoRepository.deleteById(id);
    }

    public Todo getTodoById(long id) {
        Optional<Todo> todoOptional = this.todoRepository.findById(id);
        return todoOptional.isPresent() ? todoOptional.get() : null;
    }
}
