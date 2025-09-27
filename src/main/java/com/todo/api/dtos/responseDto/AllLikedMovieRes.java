package com.todo.api.dtos.responseDto;

import com.todo.api.dtos.MovieDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(
        name = "All Liked Movie Response",
        description = "Schema for All Liked Movie"
)
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AllLikedMovieRes {

    @Schema(
            example = "[]"
    )
    private List<MovieDto> movies;

    @Schema(
            example = "true"
    )
    private final boolean isSuccess = true;
}
