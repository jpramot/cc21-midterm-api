package com.todo.api.controller;

import com.todo.api.dtos.MovieDto;
import com.todo.api.dtos.requestDto.CreateLikedMovieReq;
import com.todo.api.dtos.responseDto.AllLikedMovieRes;
import com.todo.api.dtos.responseDto.CreateLikedMovieRes;
import com.todo.api.dtos.responseDto.ErrorApiRes;
import com.todo.api.dtos.responseDto.LikedMovieRes;
import com.todo.api.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIS WITH AUTHENTICATED USER for Favorite Movies",
        description = "CRUD REST APIS for Create, Read, Delete Favorite Movies with authenticated user"
)
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
@SecurityRequirement(
        name = "BearerAuth")
@RestController
@RequestMapping("/api/V3/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Operation(
            summary = "Get All Liked Movies REST API",
            description = "REST API for get all liked movies with authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            )
    })
    @GetMapping()
    public ResponseEntity<AllLikedMovieRes> getAllLikedMovie() {
        List<MovieDto> moviesDto = movieService.getAllLikedMovies();
        AllLikedMovieRes response = new AllLikedMovieRes(moviesDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Liked Movie by Movie Id REST API",
            description = "REST API for get liked movie by movie id with authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<LikedMovieRes> getLikedMovieByMovieId(@PathVariable int movieId) {
        MovieDto movieDto = movieService.getLikedMovie(movieId);
        LikedMovieRes response = new LikedMovieRes(movieDto);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create Liked Movie REST API",
            description = "REST API for create liked movie with authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status BAD_REQUEST",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorApiRes.class)
                    )
            )
    })
    @PostMapping()
    public ResponseEntity<CreateLikedMovieRes> createLikedMovie(@RequestBody @Valid CreateLikedMovieReq body) {
        MovieDto movieDto = movieService.createLikedMovie(body);
        CreateLikedMovieRes response = new CreateLikedMovieRes(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Delete Liked Movie REST API",
            description = "REST API for delete liked movie with authenticated user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "HTTP Status NO_CONTENT"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status NOT_FOUND",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorApiRes.class)
                    )
            )
    })
    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteLikedMovie(@PathVariable int movieId) {
        movieService.deleteLikedMovie(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
