package com.todo.api.mapping;

import com.todo.api.dtos.MovieDto;
import com.todo.api.dtos.requestDto.CreateLikedMovieReq;
import com.todo.api.entity.Movie;

public class MovieMapper {

    public static MovieDto mappedToMovieDto(Movie movie, MovieDto movieDto) {
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setPosterPath(movie.getPosterPath());
        movieDto.setMovieId(movie.getMovieId());
        return movieDto;
    }

    public static Movie mappedToMovie(CreateLikedMovieReq movieDto, Movie movie) {
        movie.setTitle(movieDto.getTitle());
        movie.setPosterPath(movieDto.getPosterPath());
        movie.setMovieId(movieDto.getMovieId());
        return movie;
    }
}
