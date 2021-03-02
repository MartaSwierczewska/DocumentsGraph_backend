package com.projekt.todos;

import com.projekt.houses.House;
import com.projekt.houses.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
