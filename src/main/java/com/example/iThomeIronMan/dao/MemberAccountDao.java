package com.example.iThomeIronMan.dao;

import com.example.iThomeIronMan.model.MemberAccount;

public interface MemberAccountDao {

	public Integer add(MemberAccount memberAccount);
	public MemberAccount getMemberAccountByAccount(String account);
	public Integer update(MemberAccount memberAccount);
	
}
