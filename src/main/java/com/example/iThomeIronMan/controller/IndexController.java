package com.example.iThomeIronMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information() {

		
		return "information";
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String updatePassword() {

		
		return "updatePassword";
	}
}
