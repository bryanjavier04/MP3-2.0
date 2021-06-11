package model;


public interface DatabaseFunction {
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/seg21-db";
	String JDBC_USERNAME = "admin";
	String JDBC_PASSWORD = "qwerty123";
	
	//Test
	String INSERT_INTO_STUDENTS_INFO = "INSERT INTO student(id, name,"
			+ " course, yearLevel, unitsEnrolled) VALUES(?,?,?,?,?)";
	String SELECT_FROM_STUDENTS_INFO = "SELECT * FROM student";
	String SELECT_SEARCH_STUDENT = "SELECT * FROM student WHERE id = ";
	String DELETE_STUDENT =  "DELETE FROM student WHERE id = ?";
	
	public boolean insertRecord();
	public boolean selectRecord();
	public boolean searchStudent(String student_id);
	
}
