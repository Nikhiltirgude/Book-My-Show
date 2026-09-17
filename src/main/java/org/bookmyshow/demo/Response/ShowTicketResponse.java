package org.bookmyshow.demo.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookmyshow.demo.Enums.SeatTypes;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowTicketResponse {

    private String movieName;
    private String theaterInfo;
    private LocalDate showDate;
    private LocalTime showTime;
    private Double totalAmountPaid;
    private String seatNo;
    private SeatTypes seatTypes;

}
