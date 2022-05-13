package com.test.registration.dao;

import com.test.registration.dao.repository.CourseRepository;
import com.test.registration.dao.repository.StudentRepository;
import com.test.registration.exception.StudentCourseRegistrationException;
import com.test.registration.model.Student;
import com.test.registration.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentDAO {
	private final static Logger logger = LoggerFactory.getLogger(StudentDAO.class);

	private StudentRepository studentRepository;
	private CourseService courseService;

	@Autowired
	public StudentDAO(CourseService courseService, StudentRepository studentRepository) {
		this.courseService = courseService;
		this.studentRepository = studentRepository;
	}

	/**
	 * Save student, register to courseId and return studentId.
	 * @param courseId
	 * @param student
	 * @return
	 */
	public Long saveStudentWithCourseRegistration(Long courseId, Student student) {
		// Save student and return studentId.
		student = studentRepository.save(student);
		logger.info("Student has been saved successfully with Id:" + student.getStudentId());

		// Register student to course.
		courseService.registerStudentToCourse(courseId, student);
		logger.info("Student has been registered to courseId:" + courseId);

		return student.getStudentId();
	}

	/**
     * Get Student and delete if exists.
	 * @param studentId
	 */
	public void deleteStudent(Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if (!student.isPresent()) {
			throw new StudentCourseRegistrationException("Reason - Failed to delete student by studentId: " + studentId);
		}

		studentRepository.delete(student.get());
	}
}
