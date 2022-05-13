package com.test.registration.service;

import com.test.registration.dao.CourseDAO;
import com.test.registration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.registration.model.Course;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
	private CourseDAO courseDAO;

	@Autowired
	public CourseService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public Long saveCourse(Course course) {
		return courseDAO.saveCourse(course);
	}

	public void registerStudentToCourse(Long courseId, Student student) {
		courseDAO.registerStudentToCourse(courseId, student);
	}

	public List<Student> getAllStudentsByCourseName(String courseName) {
		return courseDAO.getAllStudentsByCourseName(courseName);
	}
}
