package com.example.iThomeIronMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.iThomeIronMan.model.Member;

@Controller
public class IndexController {

	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(@SessionAttribute("MemberSession") Member member) {

		System.err.println(member.toString());
		
		return "information";
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String updatePassword() {

		
		return "updatePassword";
	}
}
