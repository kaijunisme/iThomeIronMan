package com.example.iThomeIronMan.service;

import com.example.iThomeIronMan.model.Member;

public interface MemberService {

	// 資料庫操作
	public Integer add(Member member);
	public Member getDataByMa_id(String ma_id);
	
}
