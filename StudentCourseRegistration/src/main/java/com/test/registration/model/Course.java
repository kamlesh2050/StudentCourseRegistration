package com.test.registration.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COURSEID", unique = true, nullable = false)
	private Long courseId;

	@Column(name = "COURSENAME", nullable = false)
	@NotEmpty
	private String courseName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "StudentCourseRegistration", joinColumns = {
			@JoinColumn(name = "COURSEID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "STUDENTID", nullable = false, updatable = false) })
	private Set<Student> students;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return Objects.equals(courseId, course.courseId) && Objects.equals(courseName, course.courseName) && Objects.equals(students, course.students);
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseId=" + courseId +
				", courseName='" + courseName + '\'' +
				", students=" + students +
				'}';
	}
}
