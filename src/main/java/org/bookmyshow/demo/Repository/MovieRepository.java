package org.bookmyshow.demo.Repository;

import org.bookmyshow.demo.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    Optional<Movie> findMovieByMovieName(String movieName);
}
