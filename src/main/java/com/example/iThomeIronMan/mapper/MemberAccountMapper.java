package com.example.iThomeIronMan.mapper;

import com.example.iThomeIronMan.model.MemberAccount;

public interface MemberAccountMapper {

	public Integer add(MemberAccount memberAccount);
	public MemberAccount getMemberAccountByAccount(String account);
	public Integer update(MemberAccount memberAccount);
	
}
