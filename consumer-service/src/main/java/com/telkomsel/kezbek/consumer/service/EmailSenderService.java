package com.telkomsel.kezbek.consumer.service;


import javax.mail.MessagingException;

import com.telkomsel.kezbek.consumer.model.Transaction;

public interface EmailSenderService {
    
    public void sendEmail(Transaction transaction) throws MessagingException;
}
