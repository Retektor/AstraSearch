package com.mrretektor.astrasearch.dao.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.config.BaseIntegrationTest;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.domain.Star;
import com.mrretektor.astrasearch.domain.User;
import com.mrretektor.astrasearch.util.TestDataUtil;

@SpringBootTest
@ActiveProfiles("test")
public class StarDaoImplIntegrationTests extends BaseIntegrationTest {
	
	private StarDaoImpl underTest;
	private CelestialBodyDaoImpl celestialBodyDaoImpl;
	private ImageDaoImpl imageDaoImpl;
	private UserDaoImpl userDaoImpl;
	
	@Autowired
	public StarDaoImplIntegrationTests(StarDaoImpl underTest,
			ImageDaoImpl imageDaoImpl,
			CelestialBodyDaoImpl celestialBodyDaoImpl,
			UserDaoImpl userDaoImpl) {
		this.underTest = underTest;
		this.celestialBodyDaoImpl = celestialBodyDaoImpl;
		this.imageDaoImpl = imageDaoImpl;
		this.userDaoImpl = userDaoImpl;
	}
	
	@Test
	@Transactional
	@DirtiesContext
	public void testThatStarIsCreatedSuccessfully() {
		Star testStar = TestDataUtil.createTestStar();
		LocalDateTime time = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		CelestialBody testBody = TestDataUtil.createTestCelestialBody(1L, time, null);
		Image image = TestDataUtil.createTestImage();
		User user = TestDataUtil.createTestUser();
		
		userDaoImpl.create(user);
		imageDaoImpl.create(image);
		celestialBodyDaoImpl.create(1L, testBody);

		
		underTest.create(testStar, 1L);
	}
}
