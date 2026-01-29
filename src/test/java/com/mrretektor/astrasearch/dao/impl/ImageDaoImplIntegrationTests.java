package com.mrretektor.astrasearch.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.config.BaseIntegrationTest;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.util.TestDataUtil;

@SpringBootTest
@ActiveProfiles("test")
public class ImageDaoImplIntegrationTests extends BaseIntegrationTest {
	
	private ImageDaoImpl underTest;
	
	@Autowired
	public ImageDaoImplIntegrationTests(ImageDaoImpl underTest) {
		this.underTest = underTest;
	}
	
	@Test
	@Transactional
	@DirtiesContext
	public void testThatImageIsCreatedSuccessfully() {
		Image image = TestDataUtil.createTestImage();
		underTest.create(image);
	}
}
