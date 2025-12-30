package com.mrretektor.astrasearch.dao.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.mrretektor.astrasearch.TestDataUtil;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.User;

@ExtendWith(MockitoExtension.class)
public class CelestialBodyDaoImplTests {
	
		@Mock
		private JdbcTemplate jdbcTemplate;
		
		@InjectMocks
		private CelestialBodyDaoImpl underTest;
		
		@Test
		public void testThatCreateCelestialBodyGeneratesCorrectSql() {
			Timestamp time = Timestamp.from(Instant.now());
			User user = TestDataUtil.createTestUser();
			
			Long userId = user.getId();
			
		    CelestialBody body = TestDataUtil.createTestCelestialBody(userId, time);
		    
		    underTest.create(userId, body);
		    
		    verify(jdbcTemplate).update(any(PreparedStatementCreator.class));
		}
	}
