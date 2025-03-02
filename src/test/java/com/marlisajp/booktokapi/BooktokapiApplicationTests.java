package com.marlisajp.booktokapi;

import com.marlisajp.booktokapi.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.context.annotation.Import;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class BooktokapiApplicationTests {

	@Test
	void contextLoads() {
	}
}
