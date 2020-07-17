package com.mybank.springboot.web;

import com.mybank.springboot.dto.TransactionDto;
import com.mybank.springboot.model.Transaction;
import com.mybank.springboot.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> transactions() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/{userId}")
    public Iterable<Transaction> transactions(@PathVariable String userId) {
        return transactionService.findByReceivingUserId(userId);
    }

    @PostMapping("/transactions")
    public Transaction create(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getAmount(), transactionDto.getReference(), transactionDto.getReceivingUser());
    }
}
