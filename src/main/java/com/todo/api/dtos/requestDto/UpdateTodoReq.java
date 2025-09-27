package com.todo.api.dtos.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "UpdateTodo Req",
        description = "Schema for update todo request"
)
@Setter@Getter
public class UpdateTodoReq {

    @Schema(
            example = "learn java"
    )
    @Size(min = 3, max = 100, message = "Task name must be between 6 and 100 characters")
    @Pattern(regexp = ".*\\S.*", message = "Task name must not be blank or spaces only")
    private String taskName;

    @Schema(
            example = "false"
    )
    @NotNull
    private Boolean completed;
}
