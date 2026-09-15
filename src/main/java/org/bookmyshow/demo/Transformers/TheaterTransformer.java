package org.bookmyshow.demo.Transformers;

import org.bookmyshow.demo.Entities.Theater;
import org.bookmyshow.demo.RequestDTOs.AddTheaterRequest;

public class TheaterTransformer {

    public static Theater convertDtoToEntity(AddTheaterRequest addTheaterRequest){

        Theater theater=Theater.builder()
                .theaterName(addTheaterRequest.getTheaterName())
                .theaterAddress(addTheaterRequest.getTheaterAddress())
                .noOfScreen(addTheaterRequest.getNoOfScreen())
                .build();

        return theater;
    }
}
