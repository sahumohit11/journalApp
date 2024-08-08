package net.EngineeringProject.JournalApp.service;

import net.EngineeringProject.JournalApp.entity.User;
import net.EngineeringProject.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


//   @ParameterizedTest
//   @ValueSource(strings={
//           "ram",
//           "shyam",
//           "vipul"
//
//   })
    @ParameterizedTest
    @ArgumentsSource(UserAgrumentProvider.class)
    public void testFindByUserName(User user){
      assertTrue(userService.saveNewUser(user));
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "4,4,8",
           "6,6,4"

    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
