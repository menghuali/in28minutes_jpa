package com.in28minutes.jpa_demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.in28minutes.jpa_demo.entity.Course;
import com.in28minutes.jpa_demo.repo.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private final CourseRepository courseRepo;
	private final ApplicationContext context;

	@Value("${auto.exit:false}")
	private boolean autoExit;

	public JpaDemoApplication(CourseRepository courseRepo, ApplicationContext context) {
		this.courseRepo = courseRepo;
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Course 10001 -> {}", courseRepo.findById(10001L));
		log.info("Inserting new course -> {}", courseRepo.save(new Course("Python")));
		courseRepo.playWithEntityManager();
		// Clean exit
		if (autoExit) {
			log.info("Exiting application");
			SpringApplication.exit(context, () -> 0);
		}
	}

}
