package com.mrretektor.astrasearch.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	private Long userId;
	private String name;
	private String description;
	private BodyType bodyType;
	private LocalDateTime discoveryTime;
	private Long imageId;
	private BigDecimal rightAscension;
	private BigDecimal declination;
}
