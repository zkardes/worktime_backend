package com.oezkardes.worktime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.oezkardes.worktime")
public class WorktimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorktimeApplication.class, args);
	}

}
