package com.exams.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@EnableJpaRepositories("com.baeldung.persistence.repo")
@EntityScan("com.baeldung.persistence.model")
@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
public class ExamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsApplication.class, args);
	}

}
