package com.example.todolist.ToDo;

import com.example.todolist.exceptions.ResourceAlreadyExistsException;
import com.example.todolist.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ToDoJPAService  implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoJPAService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @Override
    public ToDo getById(Long id) {
        Optional<ToDo> todo = toDoRepository.findById(id);
        if (todo.isPresent()) {
            return todo.get();
        } else {
            throw new ResourceNotFoundException("Todo", id);
        }
    }

    @Override
    public ToDo create(ToDo newTodo) throws ResourceAlreadyExistsException {
        if (toDoRepository.existsById(newTodo.getId())) {
            throw new ResourceAlreadyExistsException("ToDo", newTodo.getId());
        } else {
            return toDoRepository.save(newTodo);
        }
    }

    @Override
    public void update(Long id, ToDo updatedTodo) throws ResourceNotFoundException {
        if (toDoRepository.existsById(id)) {
            toDoRepository.save(updatedTodo);
        } else {
            throw new ResourceNotFoundException("ToDo", id);
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("ToDo", id);
        }

    }
}
