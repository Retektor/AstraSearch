package com.mrretektor.astrasearch.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.config.BaseIntegrationTest;
import com.mrretektor.astrasearch.domain.Star;
import com.mrretektor.astrasearch.util.TestDataUtil;

@SpringBootTest
@ActiveProfiles("test")
public class StarDaoImplIntegrationTests extends BaseIntegrationTest {
	
	private StarDaoImpl underTest;
	
	@Autowired
	public StarDaoImplIntegrationTests(StarDaoImpl underTest) {
		this.underTest = underTest;
	}
	
	@Test
	@Transactional
	@DirtiesContext
	public void testThatStarIsCreatedSuccessfully() {
		Star testStar = TestDataUtil.createTestStar();
		underTest.create(testStar);
	}
}
