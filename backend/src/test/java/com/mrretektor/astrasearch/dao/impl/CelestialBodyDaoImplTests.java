package com.mrretektor.astrasearch.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.mrretektor.astrasearch.TestDataUtil;
import com.mrretektor.astrasearch.domain.CelestialBody;

@ExtendWith(MockitoExtension.class)
public class CelestialBodyDaoImplTests {
	
		@Mock
		private JdbcTemplate jdbcTemplate;
		
		@InjectMocks
		private CelestialBodyDaoImpl underTest;
		
		@Test
		public void testThatCreateCelestialBodyGeneratesCorrectSql() {
			Timestamp time = Timestamp.from(Instant.now());
		    CelestialBody body = TestDataUtil.createTestCelestialBody(time);
		    
		    underTest.create(body);
		    
		    ArgumentCaptor<PreparedStatementCreator> creatorCaptor = 
		        ArgumentCaptor.forClass(PreparedStatementCreator.class);
		    
		    verify(jdbcTemplate).update(creatorCaptor.capture());
		    
		    assertThat(creatorCaptor.getValue()).isNotNull();
		}
	}
