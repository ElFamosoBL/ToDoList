package com.example.todolist.ToDo;

import com.example.todolist.exceptions.ResourceAlreadyExistsException;
import com.example.todolist.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAll();

    ToDo getById(Long id);

    ToDo create(ToDo newTodo) throws ResourceAlreadyExistsException;

    void update(Long id, ToDo updatedTodo) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;
}
