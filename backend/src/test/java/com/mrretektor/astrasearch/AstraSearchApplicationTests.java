package com.mrretektor.astrasearch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AstraSearchApplicationTests extends BaseIntegrationTest {

    @Test
    void contextLoads() {
    }
}