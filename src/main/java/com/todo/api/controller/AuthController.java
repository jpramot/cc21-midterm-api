package com.todo.api.controller;

import com.todo.api.dtos.requestDto.LoginReq;
import com.todo.api.dtos.requestDto.RegisterReq;
import com.todo.api.dtos.responseDto.ErrorApiRes;
import com.todo.api.dtos.responseDto.LoginRes;
import com.todo.api.dtos.responseDto.RegisterRes;
import com.todo.api.service.AuthService;
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

@Tag(
        name = "Authentication",
        description = "Register and Login APIs"
)
@RestController
@RequestMapping("/api/V1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(
            summary = "Register a new user",
            description = "Register a new user to the system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
    @PostMapping("/register")
    public ResponseEntity<RegisterRes> register(
            @RequestBody @Valid
            RegisterReq body){
        RegisterRes response = authService.register(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Login a user",
            description = "Login a user to the system"
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
    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody @Valid LoginReq body){
        LoginRes response = authService.login(body);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Test API",
            description = "Test server is running"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/test")
    public String test() {
        return "App is running";
    }

}
