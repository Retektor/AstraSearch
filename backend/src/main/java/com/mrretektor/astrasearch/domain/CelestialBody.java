package com.mrretektor.astrasearch.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CelestialBody {
	private Long id;
	private String name;
	private String description;
	private BodyType bodyType;
	private Timestamp discoveryTime;
	private Long imageId;
	private BigDecimal rightAscension;
	private BigDecimal declination;
}
