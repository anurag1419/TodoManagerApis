package com.example.todoManager.Service;

import com.example.todoManager.Models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class TodoService {

    Logger logger = LoggerFactory.getLogger(TodoService.class);
    List<Todo> todos = new ArrayList<>();
    public Todo createTodo(Todo todo){
        todos.add(todo);
        logger.info("todo created");
        return todo;
    }
    public List<Todo> getAllTodos(){
        logger.info("Fetched todos");
        return todos;
    }
    public Todo getTodoById(int id){
       Optional<Todo>  fetchedTodo = todos.stream().filter(todo -> todo.getId() == id).toList().stream().findFirst();
        return fetchedTodo.orElse(null);
    }

    public Todo updateTodoById(Todo todoToBeUpdated,int id){
        logger.info("Updating the todo with id: {}",id);
        todos =  todos.stream().map(todo -> todo.getId() == id ? todoToBeUpdated : todo).toList();
        return todoToBeUpdated;
    }
    public void deleteTodoById(int id){
        logger.info("Deleting the todo with id: {}",id);
        todos.removeIf(todo -> todo.getId() == id);
    }
}
