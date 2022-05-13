package com.test.registration.exception;

public class StudentCourseRegistrationException extends IllegalStateException {
	private static final long serialVersionUID = 1L;
	
	public StudentCourseRegistrationException(String message) {
		super(message);
	}
	
	public StudentCourseRegistrationException(Throwable e) {
		super(e);
	}

}
