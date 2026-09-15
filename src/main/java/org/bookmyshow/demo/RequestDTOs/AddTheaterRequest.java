package org.bookmyshow.demo.RequestDTOs;

import lombok.Data;

@Data
public class AddTheaterRequest {

    private String theaterName;
    private String theaterAddress;
    private Integer noOfScreen;
}
