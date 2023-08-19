package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie) {
       movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
       movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String mName, String dName) {
       movieRepository.addMovieDirectorPair(mName,dName);
    }

    public Movie getMovieByName(String mName) {
       return movieRepository.getMovieByName(mName);
    }

    public Director getDirectorByName(String dName) {
      return movieRepository.getDirectorByName(dName);
    }

    public List<Movie> getMoviesByDirectorName(String dName) {
        return movieRepository.getMoviesByDirectorName(dName);
    }

    public List<Movie> findAllMovies() {
      return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String dName) {
       movieRepository.deleteDirectorByName(dName);
    }

    public void deleteAllDirectors() {
       movieRepository.deleteAllDirectors();
    }
}
