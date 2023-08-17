package com.mytutos.spring.springjdbc5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyRideTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRideTrackerApplication.class, args);
	}

}
