package model;

import java.sql.*;

public interface DatabaseFunction {
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/seg21-db";
	String JDBC_USERNAME = "root";
	String JDBC_PASSWORD = "";
	
	//Test
	String INSERT_INTO_STUDENTS_INFO = "INSERT INTO student(id, name,"
			+ " course, yearLevel, unitsEnrolled) VALUES(?,?,?,?,?)";
	String SELECT_FROM_STUDENTS_INFO = "SELECT * FROM student";
	String SELECT_SEARCH_STUDENT = "SELECT * FROM student WHERE id = ";
	
	public Connection getConnection();
	public boolean insertRecord();
	public boolean selectRecord();
	public boolean searchStudent();
	
}
