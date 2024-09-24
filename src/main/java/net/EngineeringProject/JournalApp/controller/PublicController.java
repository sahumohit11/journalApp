package net.EngineeringProject.JournalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.EngineeringProject.JournalApp.entity.User;
import net.EngineeringProject.JournalApp.service.UserDetailsServiceImpl;
import net.EngineeringProject.JournalApp.service.UserService;
import net.EngineeringProject.JournalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK..";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
             authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())) ;
           UserDetails userDetails= userDetailsService.loadUserByUsername(user.getUserName());
           String jwt= jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        }catch (Exception e){
            log.error("Exception Occurred while createAuthenticationToken",e);
            return new ResponseEntity<>("Incorrect UserName or password ",HttpStatus.BAD_REQUEST);

        }
    }
}
