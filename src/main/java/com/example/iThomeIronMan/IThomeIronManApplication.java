package com.example.iThomeIronMan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class IThomeIronManApplication {

	public static void main(String[] args) {
		SpringApplication.run(IThomeIronManApplication.class, args);
	}

}
