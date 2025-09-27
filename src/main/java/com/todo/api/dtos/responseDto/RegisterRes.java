package com.todo.api.dtos.responseDto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Register Response",
        description = "Schema that holds the response of the register endpoint"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRes {

    @Schema(
            example = "User registered successfully"
    )
    private String message;

    @Schema(
            example = "true"
    )
    private boolean isSuccess;
}
