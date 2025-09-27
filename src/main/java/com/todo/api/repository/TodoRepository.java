package com.todo.api.repository;

import com.todo.api.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByUserId(Long userId);

    Optional<Todo> findByIdAndUserId(Long id, Long userId);
}
