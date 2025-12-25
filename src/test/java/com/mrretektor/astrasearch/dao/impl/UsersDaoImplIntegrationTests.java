package com.mrretektor.astrasearch.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.TestDataUtil;
import com.mrretektor.astrasearch.config.TestContainersConfiguration;
import com.mrretektor.astrasearch.domain.Users;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(TestContainersConfiguration.class)
public class UsersDaoImplIntegrationTests {

	
	private UsersDaoImpl underTest;


	@Autowired
	public UsersDaoImplIntegrationTests(UsersDaoImpl underTest) {
		this.underTest = underTest;
	}
	
	
	@Test
	@Transactional
	public void testThatUserCanBeCreatedAndRecalled() {
		Users user = TestDataUtil.createTestUser();
		underTest.create(user);
		Optional<Users> result = underTest.findOne(user.getUsername());
		

		user.setId(1L);

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(user);
	}
}
