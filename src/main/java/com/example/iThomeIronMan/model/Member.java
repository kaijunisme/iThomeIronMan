package com.example.iThomeIronMan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member extends Base {

	private String ma_id;
	private String name;
	private String birthday;
	private String phone;
	private String address;
	
}
