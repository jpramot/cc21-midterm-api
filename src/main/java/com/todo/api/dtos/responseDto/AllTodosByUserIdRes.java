package com.todo.api.dtos.responseDto;

import com.todo.api.dtos.TodoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(
        name = "All Todo Of User",
        description = "Schema that hold all todo of user"
)
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllTodosByUserIdRes {

    @Schema(
            example = "[{\n" +
                    "  \"id\": 1,\n" +
                    "  \"taskName\": \"Buy milk\",\n" +
                    "  \"completed\": false,\n" +
                    "  \"userId\": 1,\n" +
                    "  \"createdAt\": \"2025-05-22T14:30:00\",\n" +
                    "  \"updatedAt\": \"2025-05-22T15:00:00\"\n" +
                    "}]"
    )
    private List<TodoDto> todos;
    @Schema(
            example = "true"
    )
    private final boolean isSuccess = true;
}
