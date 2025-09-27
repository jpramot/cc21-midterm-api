package com.todo.api.controller;

import com.todo.api.dtos.TodoDto;
import com.todo.api.dtos.requestDto.CreateTodoReq;
import com.todo.api.dtos.requestDto.UpdateTodoReq;
import com.todo.api.dtos.responseDto.AllTodosByUserIdRes;
import com.todo.api.dtos.responseDto.CreNUpdTodoRes;
import com.todo.api.dtos.responseDto.ErrorApiRes;
import com.todo.api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIS WITH AUTHENTICATED USER for Todos",
        description = "CRUD REST APIS for Create / Read / Update / Delete Todos use JWT Bearer Token to Authenticated User"
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/V2/todos")
public class AuthTodoController {

    @Autowired
    private TodoService todoService;

    @Operation(
            summary = "Create Todo REST API",
            description = "REST API for create todo with authenticated user"
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
        TodoDto todoDto = todoService.createTodoAuth(body);
        CreNUpdTodoRes response = new CreNUpdTodoRes(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Get All Todos REST API",
            description = "REST API for get all todos with authenticated user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping()
    public ResponseEntity<AllTodosByUserIdRes> getAllTodoByUserId() {
        List<TodoDto> todos = todoService.getAllTodoByUser();
        AllTodosByUserIdRes response = new AllTodosByUserIdRes(todos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Update Todo REST API",
            description = "REST API for update todo with authenticated user"
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
    @PatchMapping("/{id}")
    public ResponseEntity<CreNUpdTodoRes> updateTodoById(
            @PathVariable long id,
            @RequestBody @Valid UpdateTodoReq body) {
        TodoDto todoDto = todoService.updateTodoById(id, body);
        CreNUpdTodoRes response = new CreNUpdTodoRes(todoDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Delete Todo REST API",
            description = "REST API for delete todo with authenticated user"
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
