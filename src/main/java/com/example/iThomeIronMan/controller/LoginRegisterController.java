package com.example.iThomeIronMan.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.example.iThomeIronMan.model.Member;
import com.example.iThomeIronMan.model.MemberAccount;
import com.example.iThomeIronMan.service.MemberAccountService;

@Controller
public class LoginRegisterController {

	@Autowired
	private MemberAccountService memberAccountService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginRegisterController.class);
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String login(@ModelAttribute MemberAccount memberAccount) {
				
		return "login";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String doLogin(
			@ModelAttribute MemberAccount memberAccount, 
			Model model,
			HttpSession session) {
		
		Member result = memberAccountService.login(memberAccount);
		if(result == null) {
			logger.warn(memberAccount.getAccount() + "嘗試登入系統");
			return "redirect:login";
		}
		
		session.setAttribute("MemberSession", result);
		logger.info(result.getName() + "登入系統");
		return "redirect:information";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute MemberAccount memberAccount) {
		
		return "register";
	}

	@RequestMapping(value = "register", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<?> doRegister(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "checkPassword") String checkPassword,
			@Validated @ModelAttribute MemberAccount memberAccount) {
		
		if(!memberAccount.getPassword().equals(checkPassword)) {
			return new ResponseEntity<>("確認密碼與密碼不相符", HttpStatus.OK);
		}
		
		String result = memberAccountService.register(memberAccount, name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.GET})
	public String logout(HttpSession session, SessionStatus sessionStatus) {
		
		if(session.getAttribute("MemberSession") != null){
			session.removeAttribute("MemberSession");
			sessionStatus.setComplete();
		}
		
		return "redirect:login";
	}
}
