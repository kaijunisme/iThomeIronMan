package com.example.iThomeIronMan.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAccount extends Base {
	
	private String id;
	
	@Email(message = "帳號必須為電子信箱格式")
	@NotBlank(message = "帳號不可為空")
	private String account;

	@NotBlank(message = "密碼不可為空")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])[a-zA-Z]{1}[a-zA-Z0-9]{5,15}$", 
	 		 message = "密碼必須為6 至16 位英文及數字組成且首位字元為英文。")
	private String password;
	
	private String salt;
	
}
