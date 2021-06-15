package utility;
import java.sql.*;


public interface DatabaseFunction {
	//JDBC COMPONENTS
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String JDBC_CONNECTION_URL = "jdbc:mysql://localhost:3306/seg21-db";
	String JDBC_USERNAME = "admin";
	String JDBC_PASSWORD = "qwerty123";
	
	//SQL QUERIES
	String INSERT_INTO_STUDENTS_INFO = "INSERT INTO student(id, name,"
			+ " course, yearLevel, unitsEnrolled) VALUES(?,?,?,?,?)";
	String SELECT_FROM_STUDENTS_INFO = "SELECT * FROM student";
	String SELECT_SEARCH_STUDENT = "SELECT * FROM student WHERE id = ?";
	String DELETE_STUDENT =  "DELETE FROM student WHERE id = ?";
	String SELECT_REPORT_GENERATOR = "SELECT * FROM student WHERE course = ?";
	String SELECT_REPORT_GENERATOR_ALL = "SELECT * FROM student";
	String DELETE_ALL_RECORDS = "TRUNCATE TABLE student";
	String COUNT_COURSE_RECORDS = "SELECT COUNT(*) AS count FROM student WHERE course = ?";
	String SELECT_COURSE_COLUMN = "SELECT course FROM student";
	
	//SQL FUNCTIONS
	public boolean insertRecord();
	public ResultSet getRecords();
	public ResultSet searchStudent(String student_id);
	public boolean deleteStudent( String student_id);
	public ResultSet reportGenerator(String course);
	public boolean purgeRecords();
	
	
}
