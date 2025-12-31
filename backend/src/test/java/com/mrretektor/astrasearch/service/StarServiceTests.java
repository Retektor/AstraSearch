//package com.mrretektor.astrasearch.service;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.mrretektor.astrasearch.dao.CelestialBodyDao;
//import com.mrretektor.astrasearch.dao.ImageDao;
//import com.mrretektor.astrasearch.dao.StarDao;
//import com.mrretektor.astrasearch.domain.Image;
//import com.mrretektor.astrasearch.dto.request.CreateCelestialObjectRequest;
//import com.mrretektor.astrasearch.util.TestDataUtil;
//
//@ExtendWith(MockitoExtension.class)
//public class StarServiceTests {
//	
//	@Mock
//	private StarDao starDao;
//	
//	@Mock
//	private CelestialBodyDao celestialBodyDao;
//	
//	@Mock
//	private ImageDao imageDao;
//	
//	@InjectMocks
//    private ObjectCreationService underTest;
//
//	@Test
//	public void testThatCreateCelestialObjectCalledAllDependentMethods () {
//		
//		CreateCelestialObjectRequest celestialObjectRequest = TestDataUtil.createCelestialObjectRequest();
//		
//		Image image = TestDataUtil.createTestImage();
//		
//		underTest.createCelestialObject(celestialObjectRequest);
//		
//		verify(imageDao, times(1)).create(image);
//	}
//}
