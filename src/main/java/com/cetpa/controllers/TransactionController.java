package com.cetpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/activity")
public class TransactionController 
{
	@Autowired private AccountService accountService;
	
	@GetMapping("show-balance")
	public String showCurrentBalance(Model model,HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "redirect:/mybank/user/login";
		}
		int amount=accountService.getCurrentAmount((Integer)ses.getAttribute("accountNo"));
		model.addAttribute("current_amount",amount);
		return "transaction/showbalance";
	}
	@GetMapping("deposit")
	public String getDepositMoneyView(HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "redirect:/mybank/user/login";
		}
		return "transaction/deposit-money";
	}
	@GetMapping("deposit-money")
	public String depositAmount(int amount,Model model,HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "redirect:/mybank/user/login";
		}
		accountService.depositMoney(amount,(Integer)ses.getAttribute("accountNo"));
		model.addAttribute("amount",amount);
		return "transaction/deposit-success";
	}
	@GetMapping("withdraw")
	public String getWithdrawMoneyView(HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "redirect:/mybank/user/login";
		}
		return "transaction/withdraw-money";
	}
	@GetMapping("withdraw-money")
	public String withdrawAmount(int amount,Model model,HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "redirect:/mybank/user/login";
		}
		int an=(Integer)ses.getAttribute("accountNo");
		int current_amount=accountService.getCurrentAmount(an);
		if(amount>current_amount)
		{
			model.addAttribute("msg","You do not have sufficient amount");
			model.addAttribute("amt",amount);
			return "transaction/withdraw-money";
		}
		accountService.withdrawMoney(amount,an);
		model.addAttribute("amount",amount);
		return "transaction/withdraw-success";
	}
}
