package net.EngineeringProject.JournalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

@Autowired
    private EmailService  emailService;
@Test

public void testSendMail(){
    emailService.sendEmail("sahurock2345@gmail.com","Testing java mail send","Hello How r u..!");

}

}
