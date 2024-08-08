
package net.EngineeringProject.JournalApp.controller;

import net.EngineeringProject.JournalApp.entity.User;
import net.EngineeringProject.JournalApp.repository.UserRepository;
import net.EngineeringProject.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
//@GetMapping
//    public ResponseEntity<?> getAllUsers(){
//    List<User> all=userService.getAll();
//    if(all!=null && !all.equals("")){
//        return new ResponseEntity<>(all,HttpStatus.OK);
//    }
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
        User userInDb=userService.findByUserName(userName);
       // if(userInDb!=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       userRepository.deleteByUserName(authentication.getName());
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}

