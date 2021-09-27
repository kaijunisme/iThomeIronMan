package com.example.iThomeIronMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.iThomeIronMan.model.MemberAccount;

public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long> {

	public MemberAccount findByAccount(String account);
	
}
