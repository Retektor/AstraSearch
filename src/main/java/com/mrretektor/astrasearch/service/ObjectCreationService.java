package com.mrretektor.astrasearch.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrretektor.astrasearch.dto.request.CreateCelestialObjectRequest;
import com.mrretektor.astrasearch.dto.response.CreateCelestialObjectResponse;

@Service
@Transactional
public interface ObjectCreationService {
	public CreateCelestialObjectResponse createCelestialObject(CreateCelestialObjectRequest celestialObjectRequest);
}
