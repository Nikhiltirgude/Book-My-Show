package org.bookmyshow.demo.Services;

import org.bookmyshow.demo.Entities.*;
import org.bookmyshow.demo.Repository.ShowRepository;
import org.bookmyshow.demo.Repository.TicketRepository;
import org.bookmyshow.demo.Repository.UserRepository;
import org.bookmyshow.demo.RequestDTOs.BookTicketRequest;
import org.bookmyshow.demo.Response.ShowTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

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

        User user=userRepository.findByEmailId(bookTicketRequest.getEmailId()).get();

        Ticket ticket=Ticket.builder()
                .seatNoBooked(bookTicketRequest.getSeatList().toString())
                .totalAmountPaid(totalAmount)
                .show_ticket(show)
                .user(user)
                .build();


        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        ticket=ticketRepository.save(ticket);

        return "This is the ticket with ticketId : "+ticket.getTicketNo();
    }

    public  ShowTicketResponse viewTicket(Integer tickteId) throws Exception{

       Optional<Ticket>optionalTicket=ticketRepository.findById(tickteId);
       if(optionalTicket.isEmpty()){
           throw new Exception("Invalid ticketId ...");
       }
       Ticket ticket=optionalTicket.get();

       Show show=ticket.getShow_ticket();
       String movieName=show.getMovie().getMovieName();
       Theater theater=show.getTheater();
       String theaterName=theater.getTheaterName();
       String theaterAddress=theater.getTheaterAddress();
       String theaterInfo=theaterName+" , "+theaterAddress;
       String bookedSeats=ticket.getSeatNoBooked();

       ShowTicketResponse showTicketResponse=ShowTicketResponse.builder()
               .seatNo(bookedSeats)
               .theaterInfo(theaterInfo)
               .movieName(movieName)
               .showDate(show.getShowDate())
               .showTime(show.getShowTime())
               .totalAmountPaid(ticket.getTotalAmountPaid())
               .build();

       String emailId=ticket.getUser().getEmailId();

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("ntirgude@gmail.com");
        simpleMailMessage.setTo(emailId);
        simpleMailMessage.setSubject("Movie Ticket Confirmation");
        simpleMailMessage.setText(simpleMailMessage.toString());
        javaMailSender.send(simpleMailMessage);

       return showTicketResponse;
    }
}
