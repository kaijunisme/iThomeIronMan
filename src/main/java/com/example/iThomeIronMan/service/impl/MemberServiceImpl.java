package com.example.iThomeIronMan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iThomeIronMan.dao.MemberDao;
import com.example.iThomeIronMan.model.Member;
import com.example.iThomeIronMan.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	public Integer add(Member member) {
		
		return memberDao.add(member);
	}

	public Member getDataByMa_id(String ma_id) {
		
		return memberDao.getDataByMa_id(ma_id);
	}
}
