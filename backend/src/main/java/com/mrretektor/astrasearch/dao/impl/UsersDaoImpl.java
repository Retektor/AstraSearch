package com.mrretektor.astrasearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.UsersDao;
import com.mrretektor.astrasearch.domain.Users;


@Component
public class UsersDaoImpl implements UsersDao{

	private final JdbcTemplate jdbcTemplate;
	
	public UsersDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public void create(Users user) {
		jdbcTemplate.update("INSERT INTO users (username, password, first_name, last_name, email)"
				+ " VALUES (?, ?, ?, ?, ?)",
				user.getUsername(), user.getPassword(), user.getFirstName(),
				user.getLastName(), user.getEmail()
				);
	}

	
	@Override
	public Optional<Users> findOne(String username) {
		List<Users> results = jdbcTemplate.query("SELECT"
				+ " id, username, password, first_name, last_name, email"
				+ " FROM users WHERE username = ? LIMIT 1",
				new UsersRowMapper(),
				username);
		
		return results.stream().findFirst();
	}
	
	
	public static class UsersRowMapper implements RowMapper<Users> {
		
		@Override
		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Users.builder()
					.id(rs.getLong("id"))
					.username(rs.getString("username"))
					.password(rs.getString("password"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.email(rs.getString("email"))
					.build();
		}
	}
}
