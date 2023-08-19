package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
 Map<String,Movie> movieDB=new HashMap<>();
 Map<String,Director> directorDB=new HashMap<>();
 Map<String,List<Movie>> pairDB=new HashMap<>();

    public void addMovie(Movie movie) {
      movieDB.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
      directorDB.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String mName, String dName) {
       Movie movie=movieDB.get(mName);
       if(pairDB.containsKey(dName)){
           List<Movie> list=pairDB.get(dName);
           list.add(movie);
           pairDB.put(dName,list);
       }
       else{
           List<Movie> list=new ArrayList<>();
           list.add(movie);
           pairDB.put(dName,list);
       }

    }

    public Movie getMovieByName(String mName) {
      return movieDB.get(mName);
    }

    public Director getDirectorByName(String dName) {
      return directorDB.get(dName);
    }

    public List<Movie> getMoviesByDirectorName(String dName) {

      return pairDB.get(dName);
    }

    public List<Movie> findAllMovies() {
        List<Movie> list=new ArrayList<>();
        for(Map.Entry<String,Movie> entry : movieDB.entrySet()){
                list.add(entry.getValue());
        }
        return list;
    }

    public void deleteDirectorByName(String dName) {
       List<Movie> movie=pairDB.get(dName);
       pairDB.remove(dName);
       directorDB.remove(dName);

       for(Movie it : movie){
           if(movieDB.containsKey(it.getName())){
               movieDB.remove(it.getName());
           }
       }

    }

    public void deleteAllDirectors() {
//     movieDB.clear();
//     directorDB.clear();
     for(Map.Entry<String,List<Movie>> entry : pairDB.entrySet()){
         String dName= entry.getKey();
         if(directorDB.containsKey(dName)){
             directorDB.remove(dName);
             List<Movie> list=pairDB.get(dName);

             for(Movie movie: list){
                 movieDB.remove(movie.getName());
             }
         }
     }
     pairDB.clear();
    }
}
