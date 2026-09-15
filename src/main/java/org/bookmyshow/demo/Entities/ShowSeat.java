package org.bookmyshow.demo.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmyshow.demo.Enums.SeatTypes;

@Entity
@Table(name = "show_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private Double showPrice;
    private Boolean isAvailable;
    private Boolean foodAttached;

    private String seatNo; //These value will
    @Enumerated(value = EnumType.STRING)
    private SeatTypes seatType;//come from the Theater Seat
    //based on the mapping or the seat structure

    @JoinColumn
    @ManyToOne
    private Show show;
}
