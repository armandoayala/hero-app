package com.arm.api.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class HeroApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		String passwordOperator = "op_12345";
		String passwordAdmin = "admin_852@";

		String bcryptPasswordOperator = passwordEncoder.encode(passwordOperator);
		System.out.println(bcryptPasswordOperator);

		String bcryptPasswordAdmin = passwordEncoder.encode(passwordAdmin);
		System.out.println(bcryptPasswordAdmin);
        */
	}
}
