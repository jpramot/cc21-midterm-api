package com.todo.api.service;

import com.todo.api.dtos.TodoDto;
import com.todo.api.dtos.requestDto.CreateTodoReq;
import com.todo.api.dtos.requestDto.UpdateTodoReq;
import com.todo.api.entity.Todo;
import com.todo.api.entity.User;
import com.todo.api.exceptionHandler.BadRequestExc;
import com.todo.api.exceptionHandler.NotFoundExc;
import com.todo.api.mapping.TodoMapping;
import com.todo.api.repository.TodoRepository;
import com.todo.api.repository.UserRepository;
import com.todo.api.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public TodoDto createTodo(CreateTodoReq createTodoReq) {
        long id = createTodoReq.getUserId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExc("User", "id", id));
        Todo newTodo = TodoMapping.mapToTodo(createTodoReq, new Todo());
        newTodo.setUser(user);
        todoRepository.save(newTodo);

        return TodoMapping.mapToToDoDto(newTodo, new TodoDto());
    }

    @Override
    public TodoDto createTodoAuth(CreateTodoReq createTodoReq) {
        long id = authUtil.getUserId();

        if(id != createTodoReq.getUserId()) {
            throw new BadRequestExc("authenticated user id does not match with todo user id");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExc("User", "id", id));
        Todo newTodo = TodoMapping.mapToTodo(createTodoReq, new Todo());
        newTodo.setUser(user);
        todoRepository.save(newTodo);

        return TodoMapping.mapToToDoDto(newTodo, new TodoDto());
    }


    @Override
    public TodoDto updateTodoById(long id, UpdateTodoReq updateTodoReq) {
        long userId = authUtil.loginUser().getId();
        Todo existsTodo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundExc("Todo not found with id and userId"));
        existsTodo.setTaskName(updateTodoReq.getTaskName());
        existsTodo.setCompleted(updateTodoReq.getCompleted());
        todoRepository.save(existsTodo);

        return TodoMapping.mapToToDoDto(existsTodo, new TodoDto());
    }

    @Override
    public TodoDto updateTodoByIdAndUserId(long id, long userId, UpdateTodoReq body) {
        Todo existsTodo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundExc(String.format("Todo not found with id: %d and userId: %d", id, userId)));
        existsTodo.setTaskName(body.getTaskName());
        existsTodo.setCompleted(body.getCompleted());
        todoRepository.save(existsTodo);
        return TodoMapping.mapToToDoDto(existsTodo, new TodoDto());
    }

    @Override
    public List<TodoDto> getAllTodosByUserId(long userId) {
        List<Todo> todos = todoRepository.findAllByUserId(userId);
        if(!todos.isEmpty()){
        return todos.stream()
                        .map( todo -> TodoMapping.mapToToDoDto(todo, new TodoDto()))
                                .toList();
        }
        return List.of();
    }

    @Override
    public List<TodoDto> getAllTodoByUser() {
        User existsUser = authUtil.loginUser();
        List<Todo> todos = todoRepository.findAllByUserId(existsUser.getId());
        if(!todos.isEmpty()) {
            return todos.stream()
                    .map( todo -> TodoMapping.mapToToDoDto(todo, new TodoDto()))
                    .toList();
        }
        return List.of();
    }

    @Override
    public void deleteTodoById(long id) {
        long userId = authUtil.loginUser().getId();
        Todo existsTodo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundExc("Todo", "id", id));
        todoRepository.delete(existsTodo);
    }

    @Override
    public void deleteTodoByIdAndUserId(long id, long userId) {
        Todo existsTodo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new NotFoundExc(String.format("Todo not found with id: %d and userId: %d", id, userId)));
        todoRepository.delete(existsTodo);
    }


}
