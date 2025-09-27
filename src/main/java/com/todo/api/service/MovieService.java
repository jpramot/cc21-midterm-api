package com.todo.api.service;

import com.todo.api.dtos.MovieDto;
import com.todo.api.dtos.requestDto.CreateLikedMovieReq;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAllLikedMovies();

    MovieDto createLikedMovie(CreateLikedMovieReq body);

    MovieDto getLikedMovie(int movieId);

    void deleteLikedMovie(int movieId);

}
