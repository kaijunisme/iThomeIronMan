package com.example.iThomeIronMan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.iThomeIronMan.dao.MemberAccountDao;
import com.example.iThomeIronMan.model.MemberAccount;

@Repository
public class MemberAccountDaoImpl implements MemberAccountDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;

	public Integer add(MemberAccount memberAccount) {

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(memberAccount);

		String sql = " INSERT INTO test_project.member_account ( "
			  	   + "		ACCOUNT, PASSWORD, SALT, "
			  	   + "		CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
			  	   + " ) "
			  	   + " VALUE ( "
			  	   + "		:account, :password, :salt, "
			  	   + "		:create_by, NOW(), :update_by, NOW() "
			  	   + " ) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcNameTemplate.update(sql, paramSource, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public MemberAccount getMemberAccountByAccount(String account) {
		
		String sql = " SELECT "
				   + "		ID, ACCOUNT, PASSWORD, SALT, "
				   + "		CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
				   + " FROM "
				   + "		test_project.member_account "
				   + " WHERE "
				   + "		ACCOUNT = ? ";
		
		List<MemberAccount> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberAccount>(MemberAccount.class), new Object[] { account });
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public Integer update(MemberAccount memberAccount) {
		
		List<Object> args = new ArrayList<Object>();
		String sql = " UPDATE "
				   + "		test_project.member_account "
				   + " SET "
				   + "		PASSWORD = ?, UPDATE_BY = ?, UPDATE_TIME = NOW() "
				   + " WHERE "
				   + "		ID = ? "
				   + " AND "
				   + "		ACCOUNT = ? ";
		args.add(memberAccount.getPassword());
		args.add(memberAccount.getUpdate_by());
		args.add(memberAccount.getId());
		args.add(memberAccount.getAccount());
				
		return jdbcTemplate.update(sql, args.toArray());
	}
	
}
