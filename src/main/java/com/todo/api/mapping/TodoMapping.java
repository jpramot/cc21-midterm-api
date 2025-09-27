package com.todo.api.mapping;

import com.todo.api.dtos.TodoDto;
import com.todo.api.dtos.requestDto.CreateTodoReq;
import com.todo.api.entity.Todo;

public final class TodoMapping {

    public static Todo mapToTodo(CreateTodoReq createTodoReq, Todo todo) {
        todo.setTaskName(createTodoReq.getTaskName());
        return todo;
    }

    public static TodoDto mapToToDoDto(Todo todo, TodoDto todoDto) {
        todoDto.setId(todo.getId());
        todoDto.setTaskName(todo.getTaskName());
        todoDto.setCompleted(todo.getCompleted());
        todoDto.setUserId(todo.getUser().getId());
        todoDto.setCreatedAt(todo.getCreatedAt());
        todoDto.setUpdatedAt(todo.getUpdatedAt());
        return todoDto;
    }
}
