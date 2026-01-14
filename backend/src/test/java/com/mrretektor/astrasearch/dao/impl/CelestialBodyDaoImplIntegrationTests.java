package com.mrretektor.astrasearch.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

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
    
    private void assertCelestialBodyEquals(CelestialBody expected, CelestialBody actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getBodyType(), actual.getBodyType());
        
        long expectedMillis = expected.getDiscoveryTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        long actualMillis = actual.getDiscoveryTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        assertEquals(expectedMillis, actualMillis, "Discovery time mismatch");
        assertEquals(expected.getImageId(), actual.getImageId());
        assertEquals(expected.getRightAscension(), actual.getRightAscension());
        assertEquals(expected.getDeclination(), actual.getDeclination());
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
    
    @Test
    @Transactional
    @DirtiesContext
    public void testThatFindByNameReturnsBodyIfFound() {
    	LocalDateTime time = TestDataUtil.createLocalDateTime();
    	User user = userDao.create(TestDataUtil.createTestUser());
    	
    	Long userId = user.getId();
    	Long imageId = imageDao.create(TestDataUtil.createTestImage()).getId();
    	CelestialBody expectedBody = TestDataUtil.createTestCelestialBody(userId, time, imageId);
    	
    	underTest.create(userId, expectedBody);
    	
    	
    	Optional<CelestialBody> result = underTest.findByName("testName");
    	
    	
    	assertCelestialBodyEquals(expectedBody, result.get());
    }
    
    @Test
    @Transactional
    @DirtiesContext
    public void testThatFindByNameReturnsNullIfNotFound() {
    	LocalDateTime time = TestDataUtil.createLocalDateTime();
    	User user = userDao.create(TestDataUtil.createTestUser());
    	
    	Long userId = user.getId();
    	Long imageId = imageDao.create(TestDataUtil.createTestImage()).getId();
    	CelestialBody expectedBody = TestDataUtil.createTestCelestialBody(userId, time, imageId);
    	
    	underTest.create(userId, expectedBody);
    	
    	
    	Optional<CelestialBody> result = underTest.findByName("nonExistentName");
    	
    	
    	assertEquals(result, Optional.empty());
    }

}
