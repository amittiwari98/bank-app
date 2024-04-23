package com.cetpa.services;

import com.cetpa.entities.Account;
import com.cetpa.entities.User;

public interface UserService 
{
	Account createAccount(User user);
	User getUser(String userid);
}
