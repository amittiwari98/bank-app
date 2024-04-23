package com.cetpa.services;

public interface AccountService 
{
	int getAccountNoByUserId(String userid);
	int getCurrentAmount(int an);
	void depositMoney(int amount,int an);
	void withdrawMoney(int amount, int an);
}
