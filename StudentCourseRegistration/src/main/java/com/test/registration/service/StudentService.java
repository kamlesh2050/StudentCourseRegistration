package com.test.registration.service;

import java.util.*;
import com.test.registration.dao.StudentDAO;
import com.test.registration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private StudentDAO studentDAO;

	@Autowired
	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public Long saveStudentWithCourseRegistration(Long courseId, Student student) {
		return studentDAO.saveStudentWithCourseRegistration(courseId, student);
	}

	public void deleteStudent(Long studentId) {
		studentDAO.deleteStudent(studentId);
	}
}
