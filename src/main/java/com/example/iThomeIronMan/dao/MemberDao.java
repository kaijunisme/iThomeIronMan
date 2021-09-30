package com.example.iThomeIronMan.dao;

import com.example.iThomeIronMan.model.Member;

public interface MemberDao {

	public Integer add(Member member);
	public Member getDataByMa_id(String ma_id);
	
}
