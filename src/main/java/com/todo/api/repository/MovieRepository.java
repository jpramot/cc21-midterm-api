package com.todo.api.repository;

import com.todo.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByUserId(Long userId);

    Optional<Movie> findByIdAndUserId(Long id, Long userId);

    Optional<Movie> findByMovieIdAndUserId(Integer movieId, Long userId);
}
