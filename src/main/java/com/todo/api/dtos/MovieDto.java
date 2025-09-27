package com.todo.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "Movie Dto",
        description = "Schema for Movie Dto"
)
@Getter@Setter@NoArgsConstructor
public class MovieDto {

    @Schema(
            example = "1"
    )
    private long id;

    @Schema(
            example = "The Shawshank Redemption"
    )
    private String title;

    @Schema(
            example = "https://image.tmdb.org/t/p/w500/9yBkj70JjYsXs9xDXtcCH3JBBxL.jpg"
    )
    private String posterPath;

    @Schema(
            example = "1"
    )
    private Integer movieId;
}
