package org.bookmyshow.demo.Controllers;

import org.bookmyshow.demo.RequestDTOs.AddTheaterRequest;
import org.bookmyshow.demo.RequestDTOs.AddTheaterSeatRequest;
import org.bookmyshow.demo.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;


    @PostMapping("/addTheater")
    private ResponseEntity addTheater(@RequestBody AddTheaterRequest addTheaterRequest){
        try{
            String response= theaterService.addTheater(addTheaterRequest);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeat")
    private ResponseEntity addTheaterSeat(@RequestBody AddTheaterSeatRequest addTheaterSeatRequest){
        try{
            String response= theaterService.addTheaterSeat(addTheaterSeatRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
