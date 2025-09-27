package com.todo.api.controller;

import com.todo.api.dtos.TodoDto;
import com.todo.api.dtos.requestDto.CreateTodoReq;
import com.todo.api.dtos.requestDto.UpdateTodoReq;
import com.todo.api.dtos.responseDto.AllTodosByUserIdRes;
import com.todo.api.dtos.responseDto.CreNUpdTodoRes;
import com.todo.api.dtos.responseDto.ErrorApiRes;
import com.todo.api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIS WITHOUT AUTHENTICATED USER for Todos",
        description = "CRUD REST APIS for Create, Read, Update, Delete Todos without authenticated user"
)
@RestController
@RequestMapping("/api/V1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Operation(
            summary = "Create Todo REST API",
            description = "REST API for create to do without authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD_REQUEST",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorApiRes.class)
                    )
            )
    })
    @PostMapping()
    public ResponseEntity<CreNUpdTodoRes> createTodo(@RequestBody @Valid CreateTodoReq body) {
        TodoDto todoDto = todoService.createTodo(body);
        CreNUpdTodoRes response = new CreNUpdTodoRes(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get All Todos REST API",
            description = "REST API for get all to do without authenticated user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/{userId}")
    public ResponseEntity<AllTodosByUserIdRes> getAllTodoByUserId(@PathVariable long userId) {
        List<TodoDto> todos = todoService.getAllTodosByUserId(userId);
        AllTodosByUserIdRes response = new AllTodosByUserIdRes(todos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Update Todo REST API",
            description = "REST API for update to do without authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD_REQUEST",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorApiRes.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT_FOUND",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorApiRes.class)
                    )
            )
    })
    @PatchMapping("/{id}/{userId}")
    public ResponseEntity<CreNUpdTodoRes> updateTodoById(
            @PathVariable long id,
            @PathVariable long userId,
            @RequestBody @Valid UpdateTodoReq body) {
        TodoDto todoDto = todoService.updateTodoByIdAndUserId(id, userId, body);
        CreNUpdTodoRes response = new CreNUpdTodoRes(todoDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete Todo REST API",
            description = "REST API for delete to do without authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status NO_CONTENT"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT_FOUND",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorApiRes.class)
                    )
            )
    })
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable long id, @PathVariable long userId) {
        todoService.deleteTodoByIdAndUserId(id,userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
