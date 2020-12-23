package com.example.transaction.data;

import com.example.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByUsername(String username);
    List<Transaction> findByDateAndStartTime(Date date, Time startTime);
}
