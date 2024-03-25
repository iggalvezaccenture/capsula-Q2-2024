package com.ignacio.galvez.accenture.course.manager.app.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * @author ignacio.galvez
 */
@EnableJpaRepositories(CourseManagerAppApplication.PACKAGES)
@ComponentScan(basePackages = { CourseManagerAppApplication.PACKAGES })
@EntityScan(CourseManagerAppApplication.PACKAGES)
@SpringBootApplication
public class CourseManagerAppApplication {

	public static final String PACKAGES = "com.ignacio.galvez.accenture.course.manager.app.*";

	public static void main(String[] args) {
		SpringApplication.run(CourseManagerAppApplication.class, args);
	}

}
