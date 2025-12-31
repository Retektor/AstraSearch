package com.mrretektor.astrasearch.dao.impl;

import java.time.LocalDateTime;

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
import com.mrretektor.astrasearch.domain.User;
import com.mrretektor.astrasearch.util.TestDataUtil;


@SpringBootTest
@ActiveProfiles("test")
public class CelestialBodyDaoImplIntegrationTests extends BaseIntegrationTest {
    
    private CelestialBodyDaoImpl underTest;
    private final JdbcTemplate jdbcTemplate;
    private final ImageDao imageDao;
    private final UserDao userDao;
    
    
    @Autowired
    public CelestialBodyDaoImplIntegrationTests(CelestialBodyDaoImpl underTest,
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
    	LocalDateTime time = TestDataUtil.createLocalDateTime();
    	User user = TestDataUtil.createTestUser();
    	
    	userDao.create(user);
    	
    	Long imageId = imageDao.create(TestDataUtil.createTestImage()).getId();
    	
//    	TODO: REPLACE THIS BLOCK WITH MAKING DAO RETURN USER
    	Long userId = jdbcTemplate.queryForObject(
    	        "SELECT id FROM users WHERE username = ?", 
    	        Long.class, 
    	        user.getUsername()
    	    );
    	
    	CelestialBody celestialBody = TestDataUtil.createTestCelestialBody(userId, time, imageId);
    	
    	underTest.create(userId, celestialBody);
    }

}
