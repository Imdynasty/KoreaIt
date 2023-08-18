package org.koreait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Exam02Application {

	public static void main(String[] args) {
		SpringApplication.run(Exam02Application.class, args);
	}

}
