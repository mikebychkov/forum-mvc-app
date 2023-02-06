package org.mike.forum;

import lombok.extern.log4j.Log4j2;
import org.mike.forum.mongo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ForumBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
