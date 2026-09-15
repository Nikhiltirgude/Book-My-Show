package org.bookmyshow.demo.Entities;
import jakarta.persistence.*;
import lombok.*;
import org.bookmyshow.demo.Enums.Genre;
import org.bookmyshow.demo.Enums.Language;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true,nullable = false)
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language movieLanguage;

    private LocalDate releaseDate;
    private Double duration;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show>showList=new ArrayList<>();
}
