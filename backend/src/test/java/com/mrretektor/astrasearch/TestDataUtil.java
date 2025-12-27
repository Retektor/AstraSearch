package com.mrretektor.astrasearch;

import com.mrretektor.astrasearch.domain.Users;

public final class TestDataUtil {
	private TestDataUtil() {
		
	}
	
	
	public static Users createTestUser() {
		return Users.builder()
				.username("user")
				.password("password")
				.firstName("John")
				.lastName("Doe")
				.email("JohnDoe@example.com")
				.build();
	}
}
