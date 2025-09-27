package com.todo.api.dtos.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Schema(
        name = "Create Liked Movie Request",
        description = "Schema for create liked movie"
)
@Getter@Setter
public class CreateLikedMovieReq {

    @Schema(
            example = "The Shawshank Redemption"
    )
    @NotBlank
    private String title;

    @Schema(
            example = "/example.com/poster.jpg"
    )
    @NotBlank
    private String posterPath;

    @Schema(
            example = "1"
    )
    @Min(0)
    private Integer movieId;
}
