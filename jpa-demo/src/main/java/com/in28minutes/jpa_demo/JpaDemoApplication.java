package com.in28minutes.jpa_demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.in28minutes.jpa_demo.entity.Course;
import com.in28minutes.jpa_demo.entity.Review;
import com.in28minutes.jpa_demo.entity.Student;
import com.in28minutes.jpa_demo.repo.CourseRepository;
import com.in28minutes.jpa_demo.repo.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private final CourseRepository courseRepo;
	private final ApplicationContext context;
	private final StudentRepository studentRepo;

	@Value("${auto.exit:false}")
	private boolean autoExit;

	public JpaDemoApplication(CourseRepository courseRepo, ApplicationContext context, StudentRepository studentRepo) {
		this.courseRepo = courseRepo;
		this.context = context;
		this.studentRepo = studentRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Course 10001 -> {}", courseRepo.findById(10001L));
		log.info("Inserting new course -> {}", courseRepo.save(new Course("Python")));
		courseRepo.playWithEntityManager();
		studentRepo.createStudentWithPassport();
		studentRepo.updateStudent();
		studentRepo.mulitpleOperations();
		courseRepo.addReview(10002L, List
				.of(
						new Review("Great course! Clear explanations, hands-on projects, very beginner-friendly.", "5"),
						new Review("Good overview, but lacks real-world depth and advanced examples.", "3")));
		studentRepo.insertStudentAndCourse(new Student("Tony Stark"), new Course("Microservices"));
		// Clean exit
		if (autoExit) {
			log.info("Exiting application");
			SpringApplication.exit(context, () -> 0);
		}
	}

}
