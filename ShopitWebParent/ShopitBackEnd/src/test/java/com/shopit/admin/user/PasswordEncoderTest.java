package com.shopit.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	@Test
	public void testPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String userPassword = "123Bruno";
		String encodedPassword = encoder.encode(userPassword);
		System.out.println(encodedPassword);
		boolean matches = encoder.matches(userPassword, encodedPassword);
		assertThat(matches).isTrue();

	}

}
