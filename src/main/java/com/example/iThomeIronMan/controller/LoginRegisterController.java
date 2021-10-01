package com.example.iThomeIronMan.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.iThomeIronMan.model.Member;
import com.example.iThomeIronMan.model.MemberAccount;
import com.example.iThomeIronMan.service.MemberAccountService;

@Controller
public class LoginRegisterController {

	@Autowired
	private MemberAccountService memberAccountService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String login(@ModelAttribute MemberAccount memberAccount) {
				
		return "login";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String doLogin(
			@ModelAttribute MemberAccount memberAccount, 
			Model model) {
		
		Member result = memberAccountService.login(memberAccount);
		if(result == null) {
			return "redirect:login";
		}
		return "redirect:information";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute MemberAccount memberAccount) {
		
		return "register";
	}

	@RequestMapping(value = "register", method = {RequestMethod.POST})
	@ResponseBody
	public String doRegister(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "checkPassword") String checkPassword,
			@Validated @ModelAttribute MemberAccount memberAccount) {
		
		if(!memberAccount.getPassword().equals(checkPassword)) {
			return "確認密碼與密碼不相符";
		}
		
		String result = memberAccountService.register(memberAccount, name);
		return result;
	}
	
}
