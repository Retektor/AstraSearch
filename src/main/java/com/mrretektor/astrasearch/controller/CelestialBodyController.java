package com.mrretektor.astrasearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrretektor.astrasearch.dto.request.CreateCelestialObjectRequest;
import com.mrretektor.astrasearch.dto.response.CreateCelestialObjectResponse;
import com.mrretektor.astrasearch.service.ObjectCreationService;


@RestController
@RequestMapping("/api")
public class CelestialBodyController {
private final ObjectCreationService objectCreationService;
	
	@Autowired
	public CelestialBodyController (final ObjectCreationService objectCreationService) {
		this.objectCreationService = objectCreationService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<CreateCelestialObjectResponse> createCelestialObject (@RequestBody CreateCelestialObjectRequest request) {
		CreateCelestialObjectResponse response = objectCreationService.createCelestialObject(request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}
}
