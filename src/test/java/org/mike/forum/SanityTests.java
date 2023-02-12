package org.mike.forum;

import org.junit.jupiter.api.Test;
import org.mike.forum.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SanityTests {

	@Autowired
	private LoginController loginController;
	@Test
	void contextLoads() {
		assertThat(loginController).isNotNull();
	}
}
