package com.example.iThomeIronMan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IThomeIronManApplication {

	public static void main(String[] args) {
		SpringApplication.run(IThomeIronManApplication.class, args);
	}

}
