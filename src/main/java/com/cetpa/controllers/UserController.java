package com.cetpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.entities.Account;
import com.cetpa.entities.User;
import com.cetpa.services.AccountService;
import com.cetpa.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank/user")
public class UserController 
{
	@Autowired private UserService userService;
	@Autowired private AccountService accountService;
	
	@GetMapping("login")
	public String getLoginView()
	{
		return "user/user-login";
	}
	@GetMapping("create-account")
	public String getCreateAccountView()
	{
		return "user/create-account";
	}
	@PostMapping("registerme")
	public String registerUser(User user,Model model)
	{
		Account ac=userService.createAccount(user);
		model.addAttribute("ano",ac.getAccountNo());
		model.addAttribute("username",user.getName());
		return "user/registration-success";
	}
	@PostMapping("authenticate-user")
	public String authenticateUser(String userid,String password,Model model,HttpSession ses)
	{
		User user=userService.getUser(userid);
		if(user==null)
		{
			model.addAttribute("msg","Entered userid does not exist");
			model.addAttribute("uid",userid);
			return "user/user-login";
		}
		String dpassword=user.getPassword();
		if(!password.equals(dpassword))
		{
			model.addAttribute("msg","Entered password is wrong");
			model.addAttribute("uid",userid);
			return "user/user-login";
		}
		String name=user.getName();
		ses.setAttribute("username",name);
		int an=accountService.getAccountNoByUserId(userid);
		ses.setAttribute("accountNo",an);
		return "redirect:/mybank";
	}
	@GetMapping("logout")
	public String loggedUserOut(HttpSession ses,Model model)
	{
		model.addAttribute("username",ses.getAttribute("username"));
		ses.invalidate();
		return "user/logout";
	}
}
