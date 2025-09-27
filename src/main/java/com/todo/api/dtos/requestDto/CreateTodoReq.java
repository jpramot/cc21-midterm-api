package com.todo.api.dtos.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Create Todo Req",
        description = "Schema for create todo request"
)
@Getter@Setter
public class CreateTodoReq {

    @Schema(
            example = "learn javascript"
    )
    @Size(min = 3, max = 100, message = "Task name must be between 6 and 100 characters")
    @Pattern(regexp = ".*\\S.*", message = "Task name must not be blank or spaces only")
    private String taskName;

    @Schema(
            example = "1"
    )
    @NotNull
    @Positive(message = "Priority must be greater than 0")
    private Long userId;
}
