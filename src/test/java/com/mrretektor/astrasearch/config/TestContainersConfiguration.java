//package com.mrretektor.astrasearch.config;
//
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//@TestConfiguration
//public class TestContainersConfiguration {
//	
//	private static final PostgreSQLContainer<?> CONTAINER;
//    
//	static { CONTAINER = new PostgreSQLContainer<>("postgres:15-alpine")
//            .withDatabaseName("testdb")
//            .withUsername("test")
//            .withPassword("test");
//	
//        CONTAINER.start();
//        
//	}
//	
//    @Bean
//    public PostgreSQLContainer<?> postgreSQLContainer() {
//        return CONTAINER;
//    }
//}