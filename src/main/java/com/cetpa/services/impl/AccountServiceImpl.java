package com.cetpa.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cetpa.entities.Account;
import com.cetpa.entities.Transaction;
import com.cetpa.repositories.AccountRepository;
import com.cetpa.repositories.TransactionRepository;
import com.cetpa.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired private AccountRepository accountRepository;
	@Autowired private TransactionRepository transactionRepository;

	public int getAccountNoByUserId(String userid) 
	{
		int an=accountRepository.findAccountNoByUserid(userid);
		return an;
	}
	public int getCurrentAmount(int an) 
	{
		int amount=accountRepository.findAmount(an);
		return amount;
	}
	public void depositMoney(int amount, int an) 
	{
		accountRepository.updateAmount(amount,an);
		Transaction tr=new Transaction();
		tr.setAccountNo(an);
		tr.setType("credit");
		tr.setAmount(amount);
		tr.setOtherAccountNo("self");
		tr.setDate(LocalDate.now());
		tr.setTime(LocalTime.now());
		transactionRepository.save(tr);
	}
	public void withdrawMoney(int amount, int an) 
	{
		accountRepository.updateAmount1(amount,an);
		Transaction tr=new Transaction();
		tr.setAccountNo(an);
		tr.setType("debit");
		tr.setAmount(amount);
		tr.setOtherAccountNo("self");
		tr.setDate(LocalDate.now());
		tr.setTime(LocalTime.now());
		transactionRepository.save(tr);
	}
}
