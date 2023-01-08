package com.telkomsel.kezbek.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telkomsel.kezbek.consumer.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    
}
