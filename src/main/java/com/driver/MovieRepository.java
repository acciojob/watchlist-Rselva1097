package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
 Map<String,Movie> movieDB=new HashMap<>();
 Map<String,Director> directorDB=new HashMap<>();
 Map<String,List<String>> pairDB=new HashMap<>();

    public void addMovie(Movie movie) {
      movieDB.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
      directorDB.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String mName, String dName) {
       if(movieDB.containsKey(mName) && directorDB.containsKey(dName)){
           movieDB.put(mName,movieDB.get(mName));
           directorDB.put(dName,directorDB.get(dName));

           List<String> list=new ArrayList<>();

           if(pairDB.containsKey(dName)) list=pairDB.get(dName);
           list.add(mName);

           pairDB.put(dName,list);
       }
    }

    public Movie getMovieByName(String mName) {
      return movieDB.get(mName);
    }

    public Director getDirectorByName(String dName) {
      return directorDB.get(dName);
    }

    public List<String> getMoviesByDirectorName(String dName) {
      List<String> list=new ArrayList<>();

      if(pairDB.containsKey(dName)){
          list=pairDB.get(dName);
      }
      return pairDB.get(dName);
    }

    public List<String> findAllMovies() {
        List<String> list=new ArrayList<>();

        return new ArrayList<>(movieDB.keySet());
    }

    public void deleteDirectorByName(String dName) {
       List<String> movie=new ArrayList<>();

       if(pairDB.containsKey(dName)){
           movie=pairDB.get(dName);

           for(String it : movie){
               if(movieDB.containsKey(it)){
                   movieDB.remove(it);
               }
           }
           pairDB.remove(dName);
       }

       if(directorDB.containsKey(dName)){
           directorDB.remove(dName);
       }
    }

    public void deleteAllDirectors() {
        HashSet<String> set=new HashSet<>();

     for(String entry : pairDB.keySet()){
         for(String m : pairDB.get(entry)){
            set.add(m);
         }
     }

     for(String s : set){
         if(movieDB.containsKey(s)){
             movieDB.remove(s);
         }
     }
//     pairDB.clear();
    }
}
