package org.bookmyshow.demo.Controllers;

import org.bookmyshow.demo.RequestDTOs.BookTicketRequest;
import org.bookmyshow.demo.Response.ShowTicketResponse;
import org.bookmyshow.demo.Services.TicketService;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        try{
            String response=ticketService.bookTicket(bookTicketRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/viewTicket")
    private ResponseEntity viewTicket(@RequestParam("ticketId")Integer ticketId){

        try{
            ShowTicketResponse showTicketResponse=ticketService.viewTicket(ticketId);
            return new ResponseEntity(showTicketResponse,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
