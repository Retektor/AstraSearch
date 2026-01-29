package com.mrretektor.astrasearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Planet {
	private Long id;
	private float orbitalPeriodDays;
	private float earthMeanRadius;
	private float earthVolume;
	private float meanDensity;
	private float surfaceGravity;
	private float surfaceTemperatureKelvin;
}
