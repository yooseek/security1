package com.cos.security1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Security1Application {

	@Bean // 해당 메서드의 리턴오브젝트를 IOC 등록
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Security1Application.class, args);
	}

}
