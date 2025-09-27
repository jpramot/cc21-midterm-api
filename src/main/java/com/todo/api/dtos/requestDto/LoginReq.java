package com.todo.api.dtos.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "User Login Req",
        description = "Schema for login user"
)
@Getter@Setter
@NoArgsConstructor
public class LoginReq {

    @Schema(
            example = "john doe"
    )
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters")
    @Pattern(regexp = ".*\\S.*", message = "Username must not be blank or spaces only")
    private String username;

    @Schema(
            example = "johnPassword"
    )
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = ".*\\S.*", message = "Password must not be blank or spaces only")
    private String password;
}
