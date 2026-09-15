package org.bookmyshow.demo.Controllers;

import org.bookmyshow.demo.RequestDTOs.AddShowRequest;
import org.bookmyshow.demo.RequestDTOs.AddShowSeatRequest;
import org.bookmyshow.demo.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity addShow(@RequestBody AddShowRequest addShowRequest){
        try{
            String response=showService.addShow(addShowRequest);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addShowSeat")
    private ResponseEntity addShowSeat(@RequestBody AddShowSeatRequest addShowSeatRequest){
        try{

            String response=showService.addShowSeat(addShowSeatRequest);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
