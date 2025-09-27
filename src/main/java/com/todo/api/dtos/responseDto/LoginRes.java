package com.todo.api.dtos.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Schema(
        name = "Login Response",
        description = "Schema that holds login response"
)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginRes {

    @Schema(
            example = "1"
    )
    private long userId;

    @Schema(
            example = "Login Success"
    )
    private String message = "Login Success";

    @Schema(
            example = "true"
    )
    private boolean isLogin = true;

    @Schema(
            example = "access token"
    )
    private String accessToken;

    @Schema(
            example = "true"
    )
    private boolean isSuccess = true;

    public LoginRes(String accessToken, long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }
}
