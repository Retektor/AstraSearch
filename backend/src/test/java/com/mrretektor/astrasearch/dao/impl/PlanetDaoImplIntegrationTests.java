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
import com.mrretektor.astrasearch.dao.CelestialBodyDao;
import com.mrretektor.astrasearch.dao.ImageDao;
import com.mrretektor.astrasearch.dao.PlanetDao;
import com.mrretektor.astrasearch.dao.StarDao;
import com.mrretektor.astrasearch.dao.UserDao;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.domain.Planet;
import com.mrretektor.astrasearch.domain.Star;
import com.mrretektor.astrasearch.domain.User;
import com.mrretektor.astrasearch.util.TestDataUtil;

@SpringBootTest
@ActiveProfiles("test")
public class PlanetDaoImplIntegrationTests extends BaseIntegrationTest {
	
	@Autowired
	private PlanetDao underTest;
	
	@Autowired
	private CelestialBodyDao celestialBodyDao;
	
	@Autowired
	private ImageDao imageDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StarDao starDao;
	
	@Test
	@Transactional
	@DirtiesContext
	public void testThatPlanetIsCreatedSuccessfully() {
		Planet testPlanet = TestDataUtil.createTestPlanet();
		LocalDateTime time = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
		Image image = TestDataUtil.createTestImage();
		CelestialBody testBody = TestDataUtil.createTestCelestialBody(1L, time, image.getId());
		User user = TestDataUtil.createTestUser();
		Star star = TestDataUtil.createTestStar();
		
		userDao.create(user);
		imageDao.create(image);
		celestialBodyDao.create(1L, testBody);
		starDao.create(star, 1L);
		
		underTest.create(testPlanet, 1L, 1L);
	}
}
