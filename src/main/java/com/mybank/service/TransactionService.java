package com.mybank.service;

import com.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class TransactionService {

    private final String bankSlogan;

    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public List<Transaction> findByReceivingUserId(String userId) {
        return transactions.stream()
                .filter(tx -> userId.equalsIgnoreCase(tx.getReceivingUser()))
                .collect(Collectors.toList());
    }

    public Transaction create(BigDecimal amount, String reference, String receivingUser) {
        ZonedDateTime timestamp = ZonedDateTime.now();
        Transaction transaction = new Transaction(amount, timestamp, reference, bankSlogan, receivingUser);
        transactions.add(transaction);
        return transaction;
    }

}
