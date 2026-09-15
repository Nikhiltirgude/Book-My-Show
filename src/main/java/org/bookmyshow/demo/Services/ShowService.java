package org.bookmyshow.demo.Services;

import org.bookmyshow.demo.Entities.*;
import org.bookmyshow.demo.Enums.SeatTypes;
import org.bookmyshow.demo.Repository.MovieRepository;
import org.bookmyshow.demo.Repository.ShowRepository;
import org.bookmyshow.demo.Repository.TheaterRepository;
import org.bookmyshow.demo.RequestDTOs.AddShowRequest;
import org.bookmyshow.demo.RequestDTOs.AddShowSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(AddShowRequest addShowRequest) throws Exception{

        Optional<Movie>movieOptional=movieRepository.findMovieByMovieName(addShowRequest.getMovieName());
        if(movieOptional.isEmpty()){
            throw new Exception("Movie is not Present");
        }
        Movie movie=movieOptional.get();

        Optional<Theater>theaterOptional=theaterRepository.findById(addShowRequest.getTheaterId());
        if (theaterOptional.isEmpty()){
            throw new Exception("Theater is not Present");
        }
        Theater theater=theaterOptional.get();

        Show show=Show.builder()
                .showDate(addShowRequest.getShowDate())
                .showTime(addShowRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        //Bidirectional mapping
        movie.getShowList().add(show);
        theater.getShowList().add(show);
        show=showRepository.save(show);
        return "The show is added SuccessFully in DataBase with id : "+show.getShowId();
    }

    public String addShowSeat(AddShowSeatRequest addShowSeatRequest) throws Exception{

        Optional<Show>optionalShow=showRepository.findById(addShowSeatRequest.getShowId());
        if(optionalShow.isEmpty()){
            throw  new Exception("Show is not available!!");
        }
        Show show=optionalShow.get();
        Theater theater=show.getTheater();
        List<TheaterSeat>theaterSeatList=theater.getTheaterSeatList();

        List<ShowSeat>showSeatList=new ArrayList<>();

        for(TheaterSeat theaterSeat:theaterSeatList){
            String seatNo=theaterSeat.getSeatNo();
            SeatTypes seatTypes=theaterSeat.getSeatTypes();

            ShowSeat showSeat=ShowSeat.builder()
                    .foodAttached(false)
                    .isAvailable(true)
                    .show(show)
                    .seatNo(seatNo)
                    .seatType(seatTypes)
                    .build();

            if (seatTypes.equals(SeatTypes.CLASSIC)){
                showSeat.setShowPrice(addShowSeatRequest.getPriceOfClassicSeat());
            }else {
                showSeat.setShowPrice(addShowSeatRequest.getPriceOfPremiumSeat());
            }
            showSeatList.add(showSeat);
        }

        show.setShowSeatList(showSeatList);
        show=showRepository.save(show);
        return "ShowSeats is added SuccessFully!! with id: "+show.getShowId();
    }
}
