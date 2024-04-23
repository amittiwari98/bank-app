package com.cetpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("mybank")
public class HomeController 
{
	@GetMapping("")
	public String getHomeView(HttpSession ses)
	{
		if(ses.getAttribute("username")==null)
		{
			return "redirect:/mybank/user/login";
		}
		return "home";
	}
}
