package co.develhope.Email01.api.controllers;

import co.develhope.Email01.api.entities.NotificationDTO;
import co.develhope.Email01.emails.EmailService;
import co.develhope.Email01.students.Student;
import co.develhope.Email01.students.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private StudentService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO payload){
        try{
            Student studentToSendNotification = userService.getStudentById(payload.getContactId());
            if(studentToSendNotification == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find the user");
            }
            emailService.sendTo( studentToSendNotification.getEmail(), payload.getTitle(), payload.getText());
            return ResponseEntity.ok().build();
        }catch(Exception e){
            System.err.println("Error in notification controller" + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
