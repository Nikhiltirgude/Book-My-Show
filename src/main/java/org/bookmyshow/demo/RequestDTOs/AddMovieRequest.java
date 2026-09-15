package org.bookmyshow.demo.RequestDTOs;

import lombok.Data;
import org.bookmyshow.demo.Enums.Genre;
import org.bookmyshow.demo.Enums.Language;
import java.time.LocalDate;

@Data
public class AddMovieRequest {

    private String movieName;
    private Genre genre;
    private Language movieLanguage;
    private LocalDate releaseDate;
    private Double duration;
}
