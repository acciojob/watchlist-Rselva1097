package com.driver;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie has been added successfully!!!", HttpStatus.OK);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);

        return new ResponseEntity<>("New director has been added successfully!!!",HttpStatus.OK);
    }
    @PostMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie")  String mName, @RequestParam("director") String dName){
        movieService.addMovieDirectorPair(mName,dName);
        return new ResponseEntity<>("New director and Movie pair has been added successfully!!!",HttpStatus.OK);

    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String mName){
        Movie movie= movieService.getMovieByName(mName);
        return new ResponseEntity<>(movie,HttpStatus.OK);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director>  getDirectorByName(@PathVariable("name") String dName){
        Director director=movieService.getDirectorByName(dName);
        return new ResponseEntity<>(director,HttpStatus.OK);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("director") String dName){
        List<Movie> list=movieService.getMoviesByDirectorName(dName);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){
        List<Movie> list=movieService.findAllMovies();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String>  deleteDirectorByName(@RequestParam("name") String dName){
        movieService.deleteDirectorByName(dName);
        return new ResponseEntity<>("Director has been deleted successfully!!!",HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
         movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Movies and Director has been deleted successfully!!!",HttpStatus.OK);
    }
}
