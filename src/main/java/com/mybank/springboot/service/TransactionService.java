package com.mybank.springboot.service;

import com.mybank.springboot.model.Transaction;
import com.mybank.springboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Component
public class TransactionService {

    private final String bankSlogan;

    private final TransactionRepository transactionRepository;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan, TransactionRepository transactionRepository) {
        this.bankSlogan = bankSlogan;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Transactional
    public Iterable<Transaction> findByReceivingUserId(String userId) {
        return transactionRepository.findByReceivingUser(userId);
    }

    public Transaction create(BigDecimal amount, String reference, String receivingUser) {
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(amount, timestamp, reference, bankSlogan, receivingUser);

        transaction = transactionRepository.save(transaction);
        return transaction;
    }

}
