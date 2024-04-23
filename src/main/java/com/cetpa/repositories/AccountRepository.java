package com.cetpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cetpa.entities.Account;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account,Integer> 
{
	@Query("select accountNo from Account where userid=:arg")
	int findAccountNoByUserid(@Param("arg") String userid);
	@Query("select amount from Account where accountNo=:arg")
	int findAmount(@Param("arg") int an);
	@Modifying
	@Transactional
	@Query("update Account set amount=amount+:arg1 where accountNo=:arg2")
	void updateAmount(@Param("arg1") int amount,@Param("arg2") int an);
	@Modifying
	@Transactional
	@Query("update Account set amount=amount-:arg1 where accountNo=:arg2")
	void updateAmount1(@Param("arg1") int amount,@Param("arg2") int an);
}
