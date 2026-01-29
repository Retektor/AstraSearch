package com.mrretektor.astrasearch.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.config.BaseIntegrationTest;
import com.mrretektor.astrasearch.domain.User;
import com.mrretektor.astrasearch.util.TestDataUtil;


@SpringBootTest
@ActiveProfiles("test")
public class UserDaoImplIntegrationTests extends BaseIntegrationTest {
	
	private UserDaoImpl underTest;


	@Autowired
	public UserDaoImplIntegrationTests(UserDaoImpl underTest) {
		this.underTest = underTest;
	}
	
	
	@Test
	@Transactional
	@DirtiesContext
	public void testThatUserCanBeCreatedAndRecalled() {
		User testUser = TestDataUtil.createTestUser();
		underTest.create(testUser);
		Optional<User> result = underTest.findOne(testUser.getUsername());
		
		assertThat(result).isPresent();
		
		User fetchedUser = result.get();
		
		assertThat(fetchedUser.getId()).isNotNull();
		assertThat(fetchedUser.getId()).isPositive();
		
		testUser.setId(fetchedUser.getId());
		assertThat(fetchedUser).isEqualTo(testUser);
	}
}
