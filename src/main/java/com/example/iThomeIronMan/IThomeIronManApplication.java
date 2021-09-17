package com.example.iThomeIronMan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.iThomeIronMan.mapper")
public class IThomeIronManApplication {

	public static void main(String[] args) {
		SpringApplication.run(IThomeIronManApplication.class, args);
	}

}
