package com.test.registration.dao;

import com.test.registration.dao.repository.CourseRepository;
import com.test.registration.exception.StudentCourseRegistrationException;
import com.test.registration.model.Course;
import com.test.registration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseDAO {
	private final static Logger logger = LoggerFactory.getLogger(CourseDAO.class);

	private CourseRepository courseRepository;

	@Autowired
	public CourseDAO(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	/**
	 * Save course.
	 * @param course
	 * @return
	 */
	public Long saveCourse(Course course) {
		course = courseRepository.save(course);
		logger.info("Course has been saved successfully with Id:" + course.getCourseId());
		return course.getCourseId();
	}

	/**
	 * Register student to a courseId.
	 * @param courseId
	 * @param student
	 */
	public void registerStudentToCourse(Long courseId, Student student) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (!courseOptional.isPresent()) {
			throw new StudentCourseRegistrationException("Reason - Failed to register Student to the courseId: " + courseId);
		}

		// Set student and save it.
		Course course = courseOptional.get();
		if(course.getStudents() == null) {
			course.setStudents(new HashSet());
		}
		course.getStudents().add(student);
		courseRepository.save(course);
	}

	/**
	 * Get course, retrieve list of students and sort it.
	 * @param courseName
	 * @return
	 */
	public List<Student> getAllStudentsByCourseName(String courseName) {
		Optional<Course> course = getCourseByCourseName(courseName);
		if (!course.isPresent()) {
			throw new StudentCourseRegistrationException("Reason - Failed to get course by courseName: " + courseName);
		}

		// return sorted students by studentName.
		return course.get().getStudents().stream().map(student -> {student.setCourses(null); return student;}).sorted(Comparator.comparing(Student::getStudentName)).collect(Collectors.toList());
	}

	/**
	 * Get course by courseName.
	 * @param courseName
	 * @return
	 */
	public Optional<Course> getCourseByCourseName(String courseName) {
		return courseRepository.findCourseByCourseName(courseName);
	}
}
