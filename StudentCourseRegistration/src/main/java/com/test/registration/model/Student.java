package com.test.registration.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STUDENTID", unique = true, nullable = false)
	private Long studentId;

	@Column(name = "STUDENTNAME", nullable = false)
	@NotEmpty
	private String studentName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "students", cascade = CascadeType.ALL)
	private Set<Course> courses;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equals(studentId, student.studentId) && Objects.equals(studentName, student.studentName) && Objects.equals(courses, student.courses);
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentId=" + studentId +
				", studentName='" + studentName + '\'' +
				", courses=" + courses +
				'}';
	}
}
