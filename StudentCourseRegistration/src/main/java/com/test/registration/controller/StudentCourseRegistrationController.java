package com.test.registration.controller;

import java.util.List;
import javax.validation.Valid;
import com.test.registration.model.Course;
import com.test.registration.model.Student;
import com.test.registration.service.CourseService;
import com.test.registration.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentCourseRegistrationController {
	private final static Logger logger = LoggerFactory.getLogger(StudentCourseRegistrationController.class);

	private StudentService studentService;
	private CourseService courseService;

	@Autowired
	public StudentCourseRegistrationController(StudentService studentService, CourseService courseService) {
		this.studentService = studentService;
		this.courseService = courseService;
	}

	@PutMapping("/studentToCourseRegistration/{courseId}")
	public String addStudent(@PathVariable long courseId, @Valid @RequestBody Student student) {
		logger.info("addStudent() - Student Name: " + student.getStudentName());
		studentService.saveStudentWithCourseRegistration(courseId, student);
		return "Student: " + student.getStudentName() + " with course registrations has been saved successfully.";
	}

	@GetMapping("/studentsByCourseName/{courseName}")
	public List<Student> getStudentsByCourseName(@PathVariable String courseName) {
		return courseService.getAllStudentsByCourseName(courseName);
	}

	@DeleteMapping("/student/{studentId}")
	public String deleteStudent(@PathVariable Long studentId) {
		studentService.deleteStudent(studentId);
		return "Student Id: " + studentId + " has been deleted successfully.";
	}

	@PostMapping("/course")
	public String addCourse(@Valid @RequestBody Course course) {
		logger.info("addCourse() - Course Name: " + course.getCourseName());
		courseService.saveCourse(course);
		return "Course: " + course.getCourseName() + " has been saved successfully.";
	}
}
