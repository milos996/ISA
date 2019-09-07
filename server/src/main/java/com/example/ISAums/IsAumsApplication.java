package com.example.ISAums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class IsAumsApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(IsAumsApplication.class, args);
	}	
}