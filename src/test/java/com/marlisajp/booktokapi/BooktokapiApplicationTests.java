package com.marlisajp.booktokapi;

import com.marlisajp.booktokapi.config.TestConfigurationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestConfigurationConfig.class)
@ActiveProfiles("test")
class BooktokapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
