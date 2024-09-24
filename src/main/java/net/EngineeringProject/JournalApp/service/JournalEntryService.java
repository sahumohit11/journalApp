package net.EngineeringProject.JournalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.EngineeringProject.JournalApp.entity.JournalEntry;
import net.EngineeringProject.JournalApp.entity.User;
import net.EngineeringProject.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user=userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved= journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch (Exception e){
             log.error("Error",e);
           throw new RuntimeException("An Error Occured While Saving The Entry",e);

        }
    }
    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry>getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
       try{
           User user=userService.findByUserName(userName);
           removed= user.getJournalEntries().removeIf(x->x.getId().equals(id));
           if(removed){
               userService.saveUser(user);
               journalEntryRepository.deleteById(id);
           }
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An Error Occurred While Deleting the Entry. ",e);
       }
       return removed;
    }
}
