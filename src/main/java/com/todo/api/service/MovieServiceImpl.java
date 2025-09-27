package com.todo.api.service;

import com.todo.api.dtos.MovieDto;
import com.todo.api.dtos.requestDto.CreateLikedMovieReq;
import com.todo.api.entity.Movie;
import com.todo.api.entity.User;
import com.todo.api.exceptionHandler.BadRequestExc;
import com.todo.api.exceptionHandler.NotFoundExc;
import com.todo.api.mapping.MovieMapper;
import com.todo.api.repository.MovieRepository;
import com.todo.api.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public List<MovieDto> getAllLikedMovies() {

        Long userId = authUtil.getUserId();

        List<Movie> movies = movieRepository.findAllByUserId(userId);

        return movies.stream()
                .map( movie -> MovieMapper.mappedToMovieDto(movie, new MovieDto()))
                .toList();
    }

    @Override
    public MovieDto getLikedMovie(int movieId) {
        User existsUser = authUtil.loginUser();
        Movie existsMovie = movieRepository.findByMovieIdAndUserId(movieId, existsUser.getId())
                .orElse(null);
        if(existsMovie == null) {
            return null;
        }
        System.out.println(existsMovie);
        return MovieMapper.mappedToMovieDto(existsMovie, new MovieDto());
    }

    @Override
    public MovieDto createLikedMovie(CreateLikedMovieReq body) {
        User existsUser = authUtil.loginUser();
        Movie existsMovie = movieRepository.findByMovieIdAndUserId(body.getMovieId(), existsUser.getId())
                .orElse(null);
        if(existsMovie != null) {
            throw new BadRequestExc("This movie is already favorite");
        }
        Movie likedMovie = MovieMapper.mappedToMovie(body, new Movie());
        likedMovie.setUser(existsUser);
        movieRepository.save(likedMovie);
        return MovieMapper.mappedToMovieDto(likedMovie, new MovieDto());
    }

    @Override
    public void deleteLikedMovie(int movieId) {
        long userId = authUtil.getUserId();
        Movie existsMovie = movieRepository.findByMovieIdAndUserId(movieId, userId)
                .orElseThrow(() -> new NotFoundExc("Movie not found with match id and userId"));
        movieRepository.delete(existsMovie);
    }

}
