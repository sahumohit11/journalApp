package net.EngineeringProject.JournalApp.schedular;

import net.EngineeringProject.JournalApp.entity.JournalEntry;
import net.EngineeringProject.JournalApp.entity.User;
import net.EngineeringProject.JournalApp.repository.UserRepositoryImpl;
import net.EngineeringProject.JournalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchedular {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;

    public void fetchUsersAndSendSaMail(){
        List<User>users=userRepository.getUserSA();
        for(User user:users){
            List< JournalEntry>journalEntries =user.getJournalEntries();
          //  journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).collect(Collectors.toList());


        }

    }

}
