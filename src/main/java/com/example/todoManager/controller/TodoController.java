package com.example.todoManager.controller;

import com.example.todoManager.exception.ResourceNotFoundException;
import com.example.todoManager.models.Todo;
import com.example.todoManager.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private TodoService todoService;

    Random random = new Random();

    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
        int id = random.nextInt(999999);
        todo.setId(id);
        logger.info("create todo");
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping("/getAllTodos")
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        logger.info("Fetching all todos");
        return new ResponseEntity<>(todoService.getAllTodos(),HttpStatus.OK);
    }

    @GetMapping("/getTodoById/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        Todo fetchedTodo = todoService.getTodoById(todoId);
        if(fetchedTodo == null){
            throw new ResourceNotFoundException("Todo not available");
        }
        logger.info("Todo: {}, Country:{}",fetchedTodo,"UAE");
        return  new ResponseEntity<>(fetchedTodo,HttpStatus.OK);
    }

    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable int id){
        return new ResponseEntity<>(todoService.updateTodoById(todo,id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id){
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Deleted the todo",HttpStatus.OK);
    }
}
