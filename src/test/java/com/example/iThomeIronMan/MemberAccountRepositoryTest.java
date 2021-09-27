package com.example.iThomeIronMan;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import com.example.iThomeIronMan.model.MemberAccount;
import com.example.iThomeIronMan.repository.MemberAccountRepository;

@SpringBootTest
public class MemberAccountRepositoryTest {

	@Autowired
	private MemberAccountRepository memberAccountRepository;

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

		String account = "admin2";
		String password = "test1234";
		String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		String md5Password = getMd5Password(password, salt);

		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setAccount(account);
		memberAccount.setPassword(md5Password);
		memberAccount.setSalt(salt);
		memberAccount.setCreate_by(account);
		memberAccount.setUpdate_by(account);
				
		memberAccountRepository.save(memberAccount);

		List<MemberAccount> result = memberAccountRepository.findAll();
		System.out.println(result);
	}
	
	@Test
	void testFindByAccount() {

		String account = "admin";
		
		MemberAccount data = memberAccountRepository.findByAccount(account);
		System.err.println(data.toString());
	}
	
	@Test
	void testUpdatePasswordByIdAndAccount() {

		String account = "admin";
		String password = "admin1234";
		
		MemberAccount data = memberAccountRepository.findByAccount(account);
		String salt = data.getSalt();
		String md5Password = getMd5Password(password, salt);
		data.setPassword(md5Password);

		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setId(data.getId());
		memberAccount.setAccount(account);
		memberAccount.setPassword(md5Password);
		memberAccount.setCreate_by(account);
		memberAccount.setUpdate_by(account);
		
		memberAccountRepository.save(data);
		MemberAccount result = memberAccountRepository.findByAccount(account);
		System.err.println(result.toString());	
	}
	
}
