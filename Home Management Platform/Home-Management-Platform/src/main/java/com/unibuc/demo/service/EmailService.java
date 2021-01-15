package com.unibuc.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private SenderService senderService;

    public void createUser() throws IOException {
        senderService.sendMail( "cristian.teodorescu97@gmail.com","iulia");

    }

    public void createTask(String TaskName) throws IOException {
        senderService.sendMailWithTaskName( "cristian.teodorescu97@gmail.com",TaskName);

    }

}