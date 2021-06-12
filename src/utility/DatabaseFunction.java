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
	String DELETE_ALL_RECORDS = "DELETE TABLE student";
	
	//SQL FUNCTIONS
	public boolean insertRecord();
	public ResultSet printRecord();
	public ResultSet searchStudent(String student_id);
	public boolean deleteStudent(String username, String password, String student_id);
	public ResultSet reportGenerator(String username, String password, String course);
	public boolean purgeRecords(String username, String password);
	
}
