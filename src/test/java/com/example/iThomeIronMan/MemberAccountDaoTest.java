package com.example.iThomeIronMan;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import com.example.iThomeIronMan.dao.MemberAccountDao;
import com.example.iThomeIronMan.model.MemberAccount;

@SpringBootTest
public class MemberAccountDaoTest {

	@Autowired
	private MemberAccountDao memberAccountDao;

	private String getMd5Password(String password, String salt) {
		// 對password + salt 進行三重加密
		String str = password + salt;
		for (int i = 0; i < 3; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		return str;
	}
	
	@Test
	void testAdd() {

		String account = "admin";
		String password = "test1234";
		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		String md5Password = getMd5Password(password, salt);

		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setAccount(account);
		memberAccount.setPassword(md5Password);
		memberAccount.setSalt(salt);
		memberAccount.setCreate_by(account);
		memberAccount.setUpdate_by(account);
		
		Integer id = memberAccountDao.add(memberAccount);
		System.err.println(id);
	}
	
	@Test
	void testGetMemberAccountByAccount() {

		String account = "admin";
		
		MemberAccount data = memberAccountDao.getMemberAccountByAccount(account);
		System.err.println(data.toString());
	}
	
	@Test
	void testUpdate() {
		
		String account = "admin";
		String password = "admin1234";
		
		MemberAccount data = memberAccountDao.getMemberAccountByAccount(account);
		String salt = data.getSalt();
		String md5Password = getMd5Password(password, salt);

		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setId(data.getId());
		memberAccount.setAccount(account);
		memberAccount.setPassword(md5Password);
		memberAccount.setUpdate_by(account);
		
		Integer result = memberAccountDao.update(memberAccount);
		System.err.println(result);		
	}

}
