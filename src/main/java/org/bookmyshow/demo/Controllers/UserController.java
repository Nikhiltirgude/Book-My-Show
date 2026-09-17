package org.bookmyshow.demo.Controllers;

import org.bookmyshow.demo.RequestDTOs.AddUserRequest;
import org.bookmyshow.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    private String addUser(@RequestBody AddUserRequest addUserRequest){
        String response=userService.addUser(addUserRequest);
        return response;
    }
}
