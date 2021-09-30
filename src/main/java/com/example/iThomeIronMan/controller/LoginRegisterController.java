package com.example.iThomeIronMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.iThomeIronMan.model.MemberAccount;

@Controller
public class LoginRegisterController {

	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String login(@ModelAttribute MemberAccount memberAccount) {
				
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute MemberAccount memberAccount) {
		
		return "register";
	}
	
}
