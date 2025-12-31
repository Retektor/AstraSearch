package com.mrretektor.astrasearch.dao.impl;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.config.BaseIntegrationTest;
import com.mrretektor.astrasearch.dao.ImageDao;
import com.mrretektor.astrasearch.dao.UserDao;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.domain.User;
import com.mrretektor.astrasearch.util.TestDataUtil;


@SpringBootTest
@ActiveProfiles("test")
public class CelestialBodyDaoIntegrationTests extends BaseIntegrationTest {
    
    private CelestialBodyDaoImpl underTest;
    private final JdbcTemplate jdbcTemplate;
    private final ImageDao imageDao;
    private final UserDao userDao;
    
    
    @Autowired
    public CelestialBodyDaoIntegrationTests(CelestialBodyDaoImpl underTest,
    		JdbcTemplate jdbcTemplate,
    		ImageDao imageDao,
    		UserDao userDao)
    {
    	this.underTest = underTest;
    	this.jdbcTemplate = jdbcTemplate;
    	this.imageDao = imageDao;
    	this.userDao = userDao;
    }
    
    @Test
    @Transactional
    @DirtiesContext
    public void testThatCelestialBodyIsCreatedSuccessfully () {
    	Timestamp time = Timestamp.from(Instant.now());
    	User user = TestDataUtil.createTestUser();
    	Image image = TestDataUtil.createTestImage();
    	
    	userDao.create(user);
    	imageDao.create(image);
    	
    	Long userId = jdbcTemplate.queryForObject(
    	        "SELECT id FROM users WHERE username = ?", 
    	        Long.class, 
    	        user.getUsername()
    	    );
    	
    	CelestialBody celestialBody = TestDataUtil.createTestCelestialBody(userId, time);
    	
    	underTest.create(userId, celestialBody);
    }

}
