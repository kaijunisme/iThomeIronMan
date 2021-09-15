package com.example.iThomeIronMan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAccount extends Base {
	
	private String id;
	private String account;
	private String password;
	private String salt;
	
}
