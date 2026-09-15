package org.bookmyshow.demo.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmyshow.demo.Enums.SeatTypes;

@Entity
@Table(name = "theater_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TheaterSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;

    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatTypes seatTypes;

    @JoinColumn
    @ManyToOne
    private Theater theater_seat;

}
