package com.telegrambot.oscar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import service.UserServiceImp;

@SpringBootApplication
public class OscarApplication {

	public static void main(String[] args) {
		SpringApplication.run(OscarApplication.class, args);
	}
}
