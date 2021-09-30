package com.example.iThomeIronMan.service;

import com.example.iThomeIronMan.model.Member;
import com.example.iThomeIronMan.model.MemberAccount;

public interface MemberAccountService {

	// 邏輯處理
	public String register(MemberAccount memberAccount, String name);
	public Member login(MemberAccount memberAccount);
	
}
