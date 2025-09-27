package com.todo.api.dtos.responseDto;

import com.todo.api.dtos.MovieDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor
public class LikedMovieRes {

    private final boolean isSuccess = true;
    private MovieDto movie;
}
