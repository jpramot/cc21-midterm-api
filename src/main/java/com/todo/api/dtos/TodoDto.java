package com.todo.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(
        name = "Todo",
        description = "Schema that holds information about a todo"
)
@Data
@NoArgsConstructor
public class TodoDto {

    @Schema(
            example = "1"
    )
    private long id;

    @Schema(
            example = "Buy milk"
    )
    private String taskName;

    @Schema(
            example = "false"
    )
    private boolean completed;

    @Schema(
            example = "1"
    )
    private long userId;

    @Schema(
            example = "2025-05-22T14:30:00"
    )
    private LocalDateTime createdAt;

    @Schema(
            example = "2025-05-22T15:00:00"
    )
    private LocalDateTime updatedAt;
}
