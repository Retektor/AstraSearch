package com.mrretektor.astrasearch.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mrretektor.astrasearch.TestDataUtil;
import com.mrretektor.astrasearch.domain.Users;

@ExtendWith(MockitoExtension.class)
public class UsersDaoImplTests {
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	private UsersDaoImpl underTest;
	
	@Test
	public void testThatCreateUserGeneratesCorrectSql() {
		Users user = TestDataUtil.createTestUser();
				
		underTest.create(user);
		
		verify(jdbcTemplate).update(
				eq("INSERT INTO users (username, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)"),
				eq("user"), eq("password"), eq("John"), eq("Doe"), eq("JohnDoe@example.com")
				);
	}

	
	@Test
	public void testThatFindOneGeneratesCorrectSql() {
		underTest.findOne("user");
		verify(jdbcTemplate).query(
				eq("SELECT id, username, password, first_name, last_name, email FROM users WHERE username = ? LIMIT 1"),
				ArgumentMatchers.<UsersDaoImpl.UsersRowMapper>any(),
				eq("user")
		);
	}
}
