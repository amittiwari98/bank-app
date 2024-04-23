package com.cetpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cetpa.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> 
{
}
