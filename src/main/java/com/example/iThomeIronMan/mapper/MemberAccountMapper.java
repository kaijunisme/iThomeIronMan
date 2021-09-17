package com.example.iThomeIronMan.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.iThomeIronMan.model.MemberAccount;

@Mapper
public interface MemberAccountMapper {

	@Insert(" INSERT INTO test_project.member_account ( "
		  + "	ACCOUNT, PASSWORD, SALT, "
		  + "	CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
		  + " ) "
		  + " VALUES ( "
		  + "	#{account}, #{password}, #{salt}, "
		  + "	#{create_by}, NOW(), #{update_by}, NOW() "
		  + " ) ")
	public Integer add(MemberAccount memberAccount);
	
	@Select(" SELECT "
		  + "	ID, ACCOUNT, PASSWORD, SALT "
		  + " FROM "
		  + "	test_project.member_account "
		  + " WHERE "
		  + "	ACCOUNT = #{account} ")
	public MemberAccount getMemberAccountByAccount(String account);
	
	@Update(" UPDATE "
		  + "	test_project.member_account "
		  + " SET "
		  + "	PASSWORD = #{password}, UPDATE_BY = #{update_by}, UPDATE_TIME = NOW() "
		  + " WHERE "
		  + "	ID = #{id} "
		  + " AND "
		  + "	ACCOUNT = #{account} ")
	public Integer update(MemberAccount memberAccount);
	
}
