package com.example.todolist;

import com.example.todolist.ToDo.ToDo;
import com.example.todolist.ToDo.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ToDoListApplication {

	@Autowired
	private ToDoRepository toDoRepository;

	public static void main(String[] args) {SpringApplication.run(ToDoListApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpBDD() {
		return (args) -> {
			List<ToDo> todos = new ArrayList<>(){{
				add(new ToDo(1L,"ToDo en cours", Date.valueOf("2023-11-13"),"En Cours" ));
				add(new ToDo(3L,"ToDo fait", Date.valueOf("2023-11-13"),"Fait" ));
				add(new ToDo(2L,"ToDo pas commencé ", Date.valueOf("2023-11-13"),"Pas commencé" ));
			}};
			toDoRepository.saveAll(todos);
		};
	}




}
