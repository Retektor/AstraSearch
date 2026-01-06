package com.mrretektor.astrasearch.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Star {
	private Long id;
	private Long bodyId;
	private String constellation;
	private BigDecimal apparentMagnitude;
	private BigDecimal absoluteMagnitude;
	private BigDecimal massSolar;
	private BigDecimal radiusSolar;
	private BigDecimal luminositySolar;
	private float temperature;
	private String spectralClass;
}
