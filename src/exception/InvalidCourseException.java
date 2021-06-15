package exception;

import exceptions.message.InvalidCourseExceptionMessage;

public class InvalidCourseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCourseException() {
		super(InvalidCourseExceptionMessage.INVALID_COURSE_MESSAGE);
	}
	
	public InvalidCourseException(String message) {
		super(message);
	}
}
