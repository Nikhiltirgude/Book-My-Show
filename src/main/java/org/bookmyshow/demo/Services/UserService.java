package org.bookmyshow.demo.Services;

import org.bookmyshow.demo.Entities.User;
import org.bookmyshow.demo.Repository.UserRepository;
import org.bookmyshow.demo.RequestDTOs.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(AddUserRequest addUserRequest){

        User user=User.builder()
                .userName(addUserRequest.getUserName())
                .emailId(addUserRequest.getEmailid())
                .build();

        user=userRepository.save(user);
        return "User has Been successFully Saved in the DataBase with uid : "+user.getUserId();
    }
}
