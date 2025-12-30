package com.mrretektor.astrasearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
}
