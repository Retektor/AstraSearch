package com.mrretektor.astrasearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.UserDao;
import com.mrretektor.astrasearch.domain.User;


@Component
public class UserDaoImpl implements UserDao{

	private final JdbcTemplate jdbcTemplate;
	
	public UserDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public User create(User user) {
		String username = user.getUsername();
		
		String sql = "INSERT INTO users (username, password, first_name, last_name, email)"
				+ " VALUES (?, ?, ?, ?, ?)"
				+ " RETURNING id, username, password, first_name, last_name, email";
		
		try {
		return jdbcTemplate.queryForObject(sql, new UserRowMapper(),
		        user.getUsername(),
		        user.getPassword(),
		        user.getFirstName(),
		        user.getLastName(),
		        user.getEmail()
		    );
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public Optional<User> findOne(String username) {
		List<User> results = jdbcTemplate.query("SELECT"
				+ " id, username, password, first_name, last_name, email"
				+ " FROM users WHERE username = ? LIMIT 1",
				new UserRowMapper(),
				username);
		
		return results.stream().findFirst();
	}
	
	
	public static class UserRowMapper implements RowMapper<User> {
		
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return User.builder()
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
