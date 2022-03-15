package com.email.emailapi.controller;

import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome, you are good to go now";
    }

    @RequestMapping(value = "/sendemail", method = RequestMethod.POST)
    public ResponseEntity <?> sendEmail (@RequestBody EmailRequest request)
    {
        System.out.println(request);
        boolean result = this.emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
        if (result){
            return ResponseEntity.ok("email sent");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent");
        }
    }
}
