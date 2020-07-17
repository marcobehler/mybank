package com.mybank.springboot.repository;

import com.mybank.springboot.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    Iterable<Transaction> findByReceivingUser(String userId);

}
