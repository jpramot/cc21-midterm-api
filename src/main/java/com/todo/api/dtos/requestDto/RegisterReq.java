package com.todo.api.dtos.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "User Register Req",
        description = "Schema for user registration"
)
@Data
@NoArgsConstructor
public class RegisterReq {

    @Schema(
            example = "john doe"
    )
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters" )
    @Pattern(regexp = ".*\\S.*", message = "Username must not be blank or spaces only")
    private String username;

    @Schema(
            example = "johnPassword"
    )
    @Size(min = 6, max =20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = ".*\\S.*", message = "Password must not be blank or spaces only")
    private String password;

    @Schema(
            example = "johnPassword"
    )
    @Size(min = 6, max =20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = ".*\\S.*", message = "Password must not be blank or spaces only")
    private String confirmPassword;
}
