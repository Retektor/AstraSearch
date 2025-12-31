package com.mrretektor.astrasearch.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrretektor.astrasearch.domain.BodyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCelestialObjectRequest {
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("body_type")
	private BodyType bodyType;
	
	@JsonProperty("discovery_time")
	private LocalDateTime discoveryTime;
	
	@JsonProperty("image_url")
	private String imageUrl;
	
	@JsonProperty("image_caption")
	private String imageCaption;
	
	@JsonProperty("right_ascension")
	private BigDecimal rightAscension;
	
	@JsonProperty("declination")
	private BigDecimal declination;
	
	@JsonProperty("type_specific_data")
	private Map<String, Object> typeSpecificData = new HashMap<String, Object>();
}
