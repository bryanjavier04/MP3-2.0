package model;

public interface DatabaseFunction {
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/students";
	String JDBC_USERNAME = "root";
	String JDBC_PASSWORD = "";
	
	
	String INSERT_INTO_STUDENTS_INFO = "INSERT INTO student_info(student_id, lastname, firstname,"
			+ " course, year, units) VALUES(?,?,?,?,?,?)";
	String SELECT_FROM_STUDENTS_INFO = "SELECT * FROM students_info";
	String SELECT_SEARCH_STUDENT = "SELECT * FROM students_info WHERE student_id = ";
	
	

}
