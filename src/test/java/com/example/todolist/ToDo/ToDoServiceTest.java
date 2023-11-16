package com.example.todolist.ToDo;


import com.example.todolist.exceptions.ExceptionHandlingAdvice;
import com.example.todolist.exceptions.ResourceAlreadyExistsException;
import com.example.todolist.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = ToDoServiceTest.class)

public class ToDoServiceTest {



    @Autowired
    private ToDoService ToDoService;

    @MockBean
    private ToDoRepository ToDoRepository;

    private List<ToDo> todos ;

    @BeforeEach
    void setUp(){
        todos = new ArrayList<>(){{
            add(new ToDo(1L,"ToDo en cours", Date.valueOf("2023-11-13"),"En Cours" ));
            add(new ToDo(2L,"ToDo fait", Date.valueOf("2023-11-13"),"Fait" ));
            add(new ToDo(3L,"ToDo pas commencé ", Date.valueOf("2023-11-13"),"Pas commencé" ));
        }};
        when(ToDoRepository.findAll()).thenReturn(todos);
    }
    @Test
    void whenGettingAll_shouldReturn3(){
        assertEquals(3, ToDoService.getAll().size());
    }

    @Test
    void whenGettingById_shouldReturnIfExists_andBeTheSame() {
        assertAll(
                () -> assertEquals(todos.get(0), ToDoService.getById(1L)),
                () -> assertEquals(todos.get(2), ToDoService.getById(3L)),
                () -> assertThrows(ResourceNotFoundException.class, () -> ToDoService.getById(12L)),
                () -> assertThrows(ResourceNotFoundException.class, () -> ToDoService.getById(4L))
        );
    }

    @Test
    void whenCreating_ShouldReturnSame() {
        ToDo toCreate = new ToDo(4L, "Nouveau ToDo", Date.valueOf("2023-11-15"), "Pas commencé");

        assertEquals(toCreate, ToDoService.create(toCreate));
    }

    @Test
    void whenCreatingWithSameId_shouldReturnEmpty() {
        ToDo same_Todo = todos.get(0);

        assertThrows(ResourceAlreadyExistsException.class, ()->ToDoService.create(same_Todo));
    }

    @Test
    void whenUpdating_shouldModifyTodo() {
        ToDo initial_todo = todos.get(2);
        ToDo new_todo = new ToDo(2L, Date.valueOf("2023-11-16") ,"Statut");

        ToDoService.update(new_todo.getId(), new_todo);
        ToDo updated_todo = ToDoService.getById(initial_todo.getId());
        assertEquals(new_todo, updated_todo);
        assertTrue(ToDoService.getAll().contains(new_todo));
    }

    @Test
    void whenUpdatingNonExisting_shouldThrowException() {
        ToDo todo = todos.get(2);

        assertThrows(ResourceNotFoundException.class, ()->ToDoService.update(75L, todo));
    }

    @Test
    void whenDeletingExistingToDo_shouldNotBeInToDosAnymore() {
        ToDo todo = todos.get(1);
        Long id = todo.getId();

        ToDoService.delete(id);
        assertFalse(ToDoService.getAll().contains(todo));
    }

    @Test
    void whenDeletingNonExisting_shouldThrowException() {
        Long id = 68L;

        assertThrows(ResourceNotFoundException.class, ()->ToDoService.delete(id));
    }

}
