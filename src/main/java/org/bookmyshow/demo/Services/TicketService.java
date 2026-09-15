package org.bookmyshow.demo.Services;

import org.bookmyshow.demo.Entities.Show;
import org.bookmyshow.demo.Entities.ShowSeat;
import org.bookmyshow.demo.Entities.Ticket;
import org.bookmyshow.demo.Repository.ShowRepository;
import org.bookmyshow.demo.Repository.TicketRepository;
import org.bookmyshow.demo.RequestDTOs.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowRepository showRepository;


    public String bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        Optional<Show>optionalShow=showRepository.findById(bookTicketRequest.getShowId());

        if(optionalShow.isEmpty()) {
            throw new Exception("Show is not available at that moment!!");
        }
        Show show=optionalShow.get();

        List<ShowSeat> showSeatList=show.getShowSeatList();

        Double totalAmount = 0.0;
        for(String seatNoToBeBooked : bookTicketRequest.getSeatList()){

            for(ShowSeat showSeat : showSeatList){
                if(showSeat.getSeatNo().equals(seatNoToBeBooked)
                    && bookTicketRequest.getSeatTypes().equals(showSeat.getSeatType())){

                    if(showSeat.getIsAvailable()){
                        showSeat.setIsAvailable(Boolean.FALSE);
                        totalAmount+=showSeat.getShowPrice();
                    }else {
                        throw new Exception("Seat no "+showSeat.getSeatNo()+" is already Booked");
                    }
                }
            }
        }

        Ticket ticket=Ticket.builder()
                .seatNoBooked(bookTicketRequest.getSeatList().toString())
                .totalAmountPaid(totalAmount)
                .show_ticket(show)
                .build();


        show.getTicketList().add(ticket);
        ticket=ticketRepository.save(ticket);

        return "This is the ticket with ticketId : "+ticket.getTicketNo();
    }
}
