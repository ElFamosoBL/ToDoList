package com.example.todolist.ToDo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public ToDoController( ToDoService toDoService){
        this.toDoService=toDoService;
    }

    @GetMapping("")
    public List<ToDo> getAll(){
        return toDoService.getAll();
    }

    @GetMapping("/{id}")
    public ToDo getByID(@PathVariable Long id) { return toDoService.getById(id); }

    @PostMapping("")
    public ResponseEntity createTodo(@RequestBody ToDo todo) {
        ToDo create = toDoService.create(todo);
        return ResponseEntity.created(URI.create("/todos/"+create.getId().toString())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTodo(@PathVariable Long id, @RequestBody ToDo todo) {
        toDoService.update(id, todo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
