package com.todo.api.dtos.responseDto;

import com.todo.api.dtos.MovieDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor
public class CreateLikedMovieRes {

    @Schema(
            example = "true"
    )
    private final boolean isSuccess = true;

    @Schema(
            example = "{\n" +
                    "  \"id\": 1,\n" +
                    "  \"title\": \"The Shawshank Redemption\",\n" +
                    "  \"posterPath\": \"/9yBkj70JjYsXs9xDXtcCH3JBBxL.jpg\",\n" +
                    "  \"movieId\": \"2\"\n" +
                    "}"
    )
    private MovieDto movie;
}
