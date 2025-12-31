package com.mrretektor.astrasearch.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.mrretektor.astrasearch.dao.CelestialBodyDao;
import com.mrretektor.astrasearch.dao.ImageDao;
import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.dto.request.CreateCelestialObjectRequest;
import com.mrretektor.astrasearch.dto.response.CreateCelestialObjectResponse;
import com.mrretektor.astrasearch.service.ObjectCreationService;
import com.mrretektor.astrasearch.service.strategy.CelestialObjectStrategy;
import com.mrretektor.astrasearch.service.strategy.factory.CelestialObjectStrategyFactory;

@Service
public class ObjectCreationServiceImpl implements ObjectCreationService {
	
	private ImageDao imageDao;
	private CelestialBodyDao celestialBodyDao;
	CelestialObjectStrategyFactory strategyFactory;
	
	
	public ObjectCreationServiceImpl(
			ImageDao imageDao,
			CelestialBodyDao celestialBodyDao,
			CelestialObjectStrategyFactory strategyFactory)
	{
		this.imageDao = imageDao;
		this.celestialBodyDao = celestialBodyDao;
		this.strategyFactory = strategyFactory;
	}
	
	
	public CreateCelestialObjectResponse createCelestialObject(CreateCelestialObjectRequest req) {
		
		Image image = Image.builder()
				.url(req.getImageUrl())
				.caption(req.getImageCaption())
				.build();
		
		BodyType bodyType = req.getBodyType();
		
		Long imageId = imageDao.create(image).getId();
		
		CelestialBody body = CelestialBody.builder()
				.name(req.getName())
				.description(req.getDescription())
				.bodyType(bodyType)
				.discoveryTime(req.getDiscoveryTime())
				.imageId(imageId)
				.rightAscension(req.getRightAscension())
				.declination(req.getDeclination())
				.build();
		
		// TODO: REPLACE 1L AS PARAMETER TO ACTUAL USERID
		Long bodyId = celestialBodyDao.create(1L, body).getId();
		
		body.setId(bodyId);
		
		CelestialObjectStrategy strategy = strategyFactory.getStrategy(bodyType);
		
		Map<String, Object> requestSpecificData = req.getTypeSpecificData();
        Map<String, Object> responseTypeSpecificData = strategy.createObject(bodyId, requestSpecificData);
		
        CreateCelestialObjectResponse response = buildResponse(body, req.getImageUrl(), responseTypeSpecificData);
        
        return response;
	}
	
	
	private CreateCelestialObjectResponse buildResponse(
            CelestialBody celestialBody,
            String imageUrl,
            Map<String, Object> typeSpecificData) {
        
	    return CreateCelestialObjectResponse.builder()
	            .celestialBodyId(celestialBody.getId())
	            .name(celestialBody.getName())
	            .description(celestialBody.getDescription())
	            .bodyType(celestialBody.getBodyType())
	            .discoveryTime(celestialBody.getDiscoveryTime())
	            .imageUrl(imageUrl)
	            .rightAscension(celestialBody.getRightAscension())
	            .declination(celestialBody.getDeclination())
	            .typeSpecificData(typeSpecificData)
	            .build();
    }
}
