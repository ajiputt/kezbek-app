package com.telkomsel.kezbek.service;

import com.telkomsel.kezbek.model.Transaction;

public interface QueueSenderService {

    public void sendQueue(Transaction transaction);
    
}
