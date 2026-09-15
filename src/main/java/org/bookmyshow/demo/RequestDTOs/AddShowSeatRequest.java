package org.bookmyshow.demo.RequestDTOs;

import lombok.Data;

@Data
public class AddShowSeatRequest {

    private Double priceOfClassicSeat;
    private Double priceOfPremiumSeat;
    private Integer showId;
}
