package model;

import java.io.Serializable;
import java.sql.*;

public class StudentBean implements Serializable, DatabaseFunction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int totalStudents = 0;
	public static int csStudents = 0;
	public static int isStudents = 0;
	public static int itStudents = 0;
	
	private String studentId;
	private String lastName;
	private String firstName;
	private String fullName;
	private String course; 
	private int year;
	private int units;
	
	public StudentBean() {
		studentId = "";
		lastName = "";
		firstName = "";
		course = "";
		year = 0;
		units = 0;
	}
	
	public StudentBean(String studentId, String lastName, String firstName, String course
			,int year, int units) {
		this.studentId = studentId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.course = course;
		this.year = year;
		this.units = units;
		
	}
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void concatenateFirstNameLastName() {
		this.fullName = getLastName() + ", " + getFirstName();
		setFullName(this.fullName);
	}


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	//JDBC Operations
	private Connection getConnection() {
		Connection connection = null;
		
		try {
			//establish java JDBC Driver
			Class.forName(DatabaseFunction.JDBC_DRIVER);
			connection = DriverManager.getConnection(DatabaseFunction.JDBC_CONNECTION_URL, 
					DatabaseFunction.JDBC_USERNAME, DatabaseFunction.JDBC_PASSWORD);
			if(connection != null) {
				System.out.println("Connection is valid!");
			}else {
				System.out.println("Connection failed!");
			}
		}catch(ClassNotFoundException cnfe){
			System.err.println("Driver not found! " + cnfe.getMessage());
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return connection;
	}
	
	public boolean insertRecord() {
		boolean isSuccess = false;
		
		try {
			Connection connection = getConnection();
			if(connection != null) {
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.INSERT_INTO_STUDENTS_INFO);
				pstmt.setString(1, this.studentId);
				pstmt.setString(2, this.fullName);
				pstmt.setString(3, this.course);
				pstmt.setInt(4, this.year);
				pstmt.setInt(5, this.units);
				
				pstmt.executeUpdate();
				isSuccess = true;
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return isSuccess;
	}
	
	public boolean selectRecord() {
		boolean isSuccess = false;
		
		try {
			Connection connection = getConnection();
			
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(DatabaseFunction.SELECT_FROM_STUDENTS_INFO);
			
			while(rs.next()) {
				System.out.println("ID: " + rs.getString("studentId"));
				System.out.println("Lastname: " + rs.getString("lastName"));
				System.out.println("Firstname: " + rs.getString("firstName"));
				System.out.println("Course: " + rs.getString("course"));
				System.out.println("Year: " + rs.getInt("year"));
				System.out.println("Units: " + rs.getInt("units"));
				System.out.println();
			}
			
			isSuccess = true;
			
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return isSuccess;
	}
	
	public boolean searchStudent(String student_id) {
		boolean isSuccess = false;
		
		try {
			Connection connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(DatabaseFunction.SELECT_SEARCH_STUDENT + student_id);
			
			System.out.println("ID: " + rs.getString("studentId"));
			System.out.println("Lastname: " + rs.getString("lastName"));
			System.out.println("Firstname: " + rs.getString("firstName"));
			System.out.println("Course: " + rs.getString("course"));
			System.out.println("Year: " + rs.getInt("year"));
			System.out.println("Units: " + rs.getInt("units"));
			
			isSuccess = true;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return isSuccess;
	}
	
	public boolean deleteStudent(String username, String password,String student_id) {
		boolean isSuccess = false;
		
		if(username.equals(DatabaseFunction.JDBC_USERNAME) && password.equals(DatabaseFunction.JDBC_PASSWORD)) {
			try {
				Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.DELETE_STUDENT);
				pstmt.setString(1, student_id);
				
				StudentBean.totalStudents = pstmt.executeUpdate();
				System.out.println("Please wait... searching for student record " + student_id);
				System.out.println("Record found and successfully deleted!");
				
				isSuccess = true;
				
				
			}catch(SQLException sqle) {
				System.err.println(sqle.getMessage());
				
			}
			
		}
		
		
		
		return isSuccess;
	}

}
