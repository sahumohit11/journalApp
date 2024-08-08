package net.EngineeringProject.JournalApp.repository;

import net.EngineeringProject.JournalApp.entity.JournalEntry;
import net.EngineeringProject.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);
    void deleteByUserName(String userName);

}
