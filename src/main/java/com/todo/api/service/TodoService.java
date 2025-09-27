package com.todo.api.service;

import com.todo.api.dtos.TodoDto;
import com.todo.api.dtos.requestDto.CreateTodoReq;
import com.todo.api.dtos.requestDto.UpdateTodoReq;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(CreateTodoReq createTodoReq);

    TodoDto createTodoAuth(CreateTodoReq createTodoReq);

    TodoDto updateTodoById(long id, UpdateTodoReq updateTodoReq);

    TodoDto updateTodoByIdAndUserId(long id, long userId, UpdateTodoReq body);

    List<TodoDto> getAllTodosByUserId(long userId);

    List<TodoDto> getAllTodoByUser();

    void deleteTodoById(long id);

    void deleteTodoByIdAndUserId(long id, long userId);
}
