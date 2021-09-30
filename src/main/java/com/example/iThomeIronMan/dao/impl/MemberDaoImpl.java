package com.example.iThomeIronMan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.iThomeIronMan.dao.MemberDao;
import com.example.iThomeIronMan.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate jdbcNameTemplate;
	
	public Integer add(Member member) {

		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(member);

		String sql = " INSERT INTO test_project.member ( "
			  	   + "		MA_ID, NAME, BIRTHDAY, PHONE, ADDRESS, "
			  	   + "		CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
			  	   + " ) "
			  	   + " VALUES ( "
			  	   + "		:ma_id, :name, :birthday, :phone, :address, "
			  	   + "		:create_by, NOW(), :update_by, NOW() "
			  	   + " ) ";

		return jdbcNameTemplate.update(sql, paramSource);
	}

	public Member getDataByMa_id(String ma_id) {
		
		String sql = " SELECT "
				   + "		MA_ID, NAME, BIRTHDAY, PHONE, ADDRESS, "
				   + "		CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME "
				   + " FROM "
				   + "		test_project.member "
				   + " WHERE "
				   + "		MA_ID = ? ";

		List<Member> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Member>(Member.class), new Object[] { ma_id });
		if(result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
}
