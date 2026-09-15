package org.bookmyshow.demo.RequestDTOs;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class AddTheaterSeatRequest {

    private Integer theaterId;
    private Integer noOfClassicSeat;
    private Integer noOfPremiumSeat;
}
