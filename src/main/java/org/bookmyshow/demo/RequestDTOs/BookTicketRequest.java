package org.bookmyshow.demo.RequestDTOs;


import lombok.Data;
import org.bookmyshow.demo.Entities.ShowSeat;
import org.bookmyshow.demo.Enums.SeatTypes;

import java.util.List;

@Data
public class BookTicketRequest {

    private Integer showId;
    private List<String> seatList;
    private SeatTypes seatTypes;
}
