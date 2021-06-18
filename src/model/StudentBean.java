package model;

import utility.*;
import java.sql.*;

public class StudentBean{
	
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
	private String username = "admin";
	private String password = "qwerty123";
		

	public String getStudentId() {
		return Security.decrypt(this.studentId);
	}

	public void setStudentId(String studentId) {
		this.studentId = Security.encrypt(studentId);
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
		this.fullName = this.lastName + this.firstName;
		this.fullName = Security.encrypt(this.fullName);
	}

	public String getFullName() {
		Security.decrypt(fullName);
		return fullName;
	}

	public void setFullName(String fullName) {
		Security.encrypt(fullName);
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
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seg21-db", 
					this.username, this.password);
		}catch(ClassNotFoundException cnfe){
			System.err.println("Driver not found! " + cnfe.getMessage());
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return connection;
	}
	
	public static void courseCounter(String course) {
		String actualCourse = course.toString();
		if(actualCourse.equals("BS CS")) {
			StudentBean.csStudents++;
		}else if(actualCourse.equals("BS IT")) {
			StudentBean.itStudents++;
		}else if(actualCourse.equals("BS IS")) {
			StudentBean.isStudents++;
		}
		StudentBean.totalStudents = StudentBean.csStudents + StudentBean.itStudents
				+ StudentBean.isStudents;
	}
	
	public boolean insertRecord() {
		boolean isSuccess = false;
		Connection connection = getConnection();
		try {
			if(connection != null) {
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO student(id, name,"
						+ " course, yearLevel, unitsEnrolled) VALUES(?,?,?,?,?)");
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
	
	public ResultSet getRecords() {
		ResultSet records = null;
		
		try {
			Connection connection = getConnection();
			
			if(connection != null) {
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM student");
				records = pstmt.executeQuery();
			}
			
		
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return records;
	}
	
	public ResultSet searchStudent(String student_id) {
		ResultSet records = null;
		try {
			Connection connection = getConnection();
			
			if(connection != null) {
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
				pstmt.setString(1, Security.encrypt(student_id));
				records = pstmt.executeQuery();
				
			}
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return records;
	}
	
	public boolean deleteStudent(String student_id) {
		boolean isSuccess = false;
			try {
				Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement("DELETE FROM student WHERE id = ?");
				pstmt.setString(1, Security.encrypt(student_id));
				if(pstmt.executeUpdate() != 0) {
					isSuccess = true;
				}
					
			}catch(SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
			
		return isSuccess;
	}
	
	public ResultSet reportGenerator(String course) {
		ResultSet records = null;
		
		try {
			Connection connection = getConnection();
			
			if(course.equals("ALL")) {
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM student");
					
				records = pstmt.executeQuery();
			}else{
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM student WHERE course = ?");
				pstmt.setString(1, course);
				
				records = pstmt.executeQuery();
			}
			
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return records;
	}
	
	
	public boolean purgeRecords() {
		boolean isSuccess = false;
		try {
			Connection connection = getConnection();
				PreparedStatement pstmt = connection.prepareStatement("TRUNCATE TABLE student");
				pstmt.executeUpdate();
				isSuccess = true;
			
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return isSuccess;
	}
	
	



}
