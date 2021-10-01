package com.example.iThomeIronMan.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.example.iThomeIronMan.dao.MemberAccountDao;
import com.example.iThomeIronMan.model.Member;
import com.example.iThomeIronMan.model.MemberAccount;
import com.example.iThomeIronMan.service.MemberAccountService;
import com.example.iThomeIronMan.service.MemberService;

@Service
public class MemberAccountServiceImpl implements MemberAccountService {

	@Autowired
	private MemberAccountDao memberAccountDao;
	
	@Autowired
	private MemberService memberService;

	private String getMd5Password(String password, String salt) {
		// 對password + salt 進行三次加密
		String str = password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	
	@Transactional
	public String register(MemberAccount memberAccount, String name) {
		
		// 檢查帳號是否已被註冊
		MemberAccount data = memberAccountDao.getMemberAccountByAccount(memberAccount.getAccount());
		if(data != null) {
			return "該帳號已被註冊";
		}

		// 產生鹽值
		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		memberAccount.setSalt(salt);
		
		// 密碼加密
		String md5Password = getMd5Password(memberAccount.getPassword(), salt);
		memberAccount.setPassword(md5Password);

		// 新增帳號
		memberAccount.setCreate_by(memberAccount.getAccount());
		memberAccount.setUpdate_by(memberAccount.getAccount());
		Integer id = memberAccountDao.add(memberAccount);
		if(id == 0) {
			return "新增帳號時發生錯誤";
		}

		// 新增會員資訊
		Member member = new Member();
		member.setMa_id(String.valueOf(id));
		member.setName(name);
		member.setCreate_by(memberAccount.getAccount());
		member.setUpdate_by(memberAccount.getAccount());
		Integer result = memberService.add(member);
		if(result == 0) {
			return "新增帳號時發生錯誤";
		}

		return null;
	}

	public Member login(MemberAccount memberAccount) {
		
		// 檢查帳號是否存在
		MemberAccount data = memberAccountDao.getMemberAccountByAccount(memberAccount.getAccount());
		if(data == null) {
			return null;
		}

		// 密碼加密
		String md5Password = getMd5Password(memberAccount.getPassword(), data.getSalt());

		// 比對密碼
		if(!data.getPassword().equals(md5Password)) {
			return null;
		}

		// 取得會員資訊
		return memberService.getDataByMa_id(data.getId());
	}
}
