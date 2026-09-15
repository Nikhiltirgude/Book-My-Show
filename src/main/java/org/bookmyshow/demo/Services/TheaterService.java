package org.bookmyshow.demo.Services;

import org.bookmyshow.demo.Entities.Theater;
import org.bookmyshow.demo.Entities.TheaterSeat;
import org.bookmyshow.demo.Enums.SeatTypes;
import org.bookmyshow.demo.Repository.TheaterRepository;
import org.bookmyshow.demo.RequestDTOs.AddTheaterRequest;
import org.bookmyshow.demo.RequestDTOs.AddTheaterSeatRequest;
import org.bookmyshow.demo.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){

        Theater theater= TheaterTransformer.convertDtoToEntity(addTheaterRequest);
        theater = theaterRepository.save(theater);
        return "Theater is registered in the Database succesfully! with theater Id : "+theater.getTheaterId();
    }

    public String addTheaterSeat(AddTheaterSeatRequest addTheaterSeatRequest){

        int noOfClaasicSeat=addTheaterSeatRequest.getNoOfClassicSeat();
        int noOfPremiumSeat=addTheaterSeatRequest.getNoOfPremiumSeat();

        Theater theater=theaterRepository.findById(addTheaterSeatRequest.getTheaterId()).get();

        int quoClassic=noOfClaasicSeat/5;
        int remClassic=noOfClaasicSeat%5;

        List<TheaterSeat>theaterSeatList=new ArrayList<>();

        for(int i=1;i<=quoClassic;i++) {
            for (int col = 1; col <= 5; col++) {
                char ch = (char) ('A' + (col - 1));
                String seatNo = i + "" + ch;
                TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                        .seatNo(seatNo)
                        .seatTypes(SeatTypes.CLASSIC)
                        .theater_seat(theater)
                        .build();
                theaterSeatList.add(theaterSeatEntity);
            }
        }

        int rowNoForReaimder=quoClassic+1;
        for (int col=1;col<=remClassic;col++){
            char ch = (char) ('A' + (col - 1));
            String seatNo = rowNoForReaimder + "" + ch;
            TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatTypes(SeatTypes.CLASSIC)
                    .theater_seat(theater)
                    .build();
            theaterSeatList.add(theaterSeatEntity);
        }

        int quoPremium=noOfPremiumSeat/5;
        int remPremium=noOfPremiumSeat%5;

        for(int i=1;i<=quoPremium;i++) {
            for (int col = 1; col <= 5; col++) {
                char ch = (char) ('A' + (col - 1));
                String seatNo = i + "" + ch;
                TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                        .seatNo(seatNo)
                        .seatTypes(SeatTypes.PREMIUM)
                        .theater_seat(theater)
                        .build();
                theaterSeatList.add(theaterSeatEntity);
            }
        }

        int rowNoForReaimder2=quoClassic+1;
        for (int col=1;col<=remPremium;col++){
            char ch = (char) ('A' + (col - 1));
            String seatNo = rowNoForReaimder2 + "" + ch;
            TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatTypes(SeatTypes.PREMIUM)
                    .theater_seat(theater)
                    .build();
            theaterSeatList.add(theaterSeatEntity);
        }

        theater.setTheaterSeatList(theaterSeatList);

        theaterRepository.save(theater);
        return "Theater has Been saved in DataBase";
    }
}
