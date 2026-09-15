package org.bookmyshow.demo.Services;

import org.bookmyshow.demo.Entities.Movie;
import org.bookmyshow.demo.Repository.MovieRepository;
import org.bookmyshow.demo.RequestDTOs.AddMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest){

        Movie movie=Movie.builder()
                .movieLanguage(addMovieRequest.getMovieLanguage())
                .movieName(addMovieRequest.getMovieName())
                .duration(addMovieRequest.getDuration())
                .releaseDate(addMovieRequest.getReleaseDate())
                .genre(addMovieRequest.getGenre())
                .build();

        movie=movieRepository.save(movie);
        return "Movie has been SuccessFully !! saved in the database with id : "+movie.getMovieId();
    }

}
