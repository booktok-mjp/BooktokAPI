package com.marlisajp.booktokapi;

import com.marlisajp.booktokapi.book.BookTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BookTest.class,properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
@ActiveProfiles("test")
class BooktokapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
