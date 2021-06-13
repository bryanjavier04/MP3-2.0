package model;

import java.io.Serializable;
import utility.*;
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
	
	public String capitalize(String str) {
		String words[]=str.split("\\s");  
	    String capitalizeWord=""; 
		
		for(String w:words){  
	        String first=w.substring(0,1);  
	        String afterfirst=w.substring(1);  
	        capitalizeWord+=first.toUpperCase()+afterfirst+" ";  
	    }  
		
		return capitalizeWord.trim();  
	}
	
	public static int getCsStudents() {
		return csStudents;
	}

	public static void setCsStudents() {
		++csStudents;
	}

	public static int getIsStudents() {
		return isStudents;
	}

	public static void setIsStudents() {
		++isStudents;
	}

	public static int getItStudents() {
		return itStudents;
	}

	public static void setItStudents() {
		++itStudents;
	}

	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getLastName() {
		Security.decrypt(lastName);
		return lastName;
	}

	public void setLastName(String lastName) {
		lastName = capitalize(lastName);
		lastName = Security.encrypt(lastName);
		this.lastName = lastName;
	}

	public String getFirstName() {
		Security.decrypt(firstName);
		return firstName;
	}

	public void setFirstName(String firstName) {
		firstName = capitalize(firstName);
		firstName = Security.encrypt(firstName);
		this.firstName = firstName;
	}
	public void concatenateFirstNameLastName() {
		this.fullName = this.lastName + ", " + this.firstName;
	}

	public String getFullName() {
		Security.decrypt(fullName);
		return fullName;
	}

	public void setFullName(String fullName) {
		fullName = Security.encrypt(fullName);
		this.fullName = fullName;
	}

	public String getCourse() {
		Security.decrypt(course);
		return course;
	}

	public void setCourse(String course) {
		course = course.toUpperCase();
		course = Security.encrypt(course);
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
				System.out.println("\nConnection is valid!");
			}else {
				System.out.println("\nConnection failed!");
			}
		}catch(ClassNotFoundException cnfe){
			System.err.println("Driver not found! " + cnfe.getMessage());
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return connection;
	}
	
	//ADMIN LOGIN
	public Connection adminConnection(String adminUserName, String adminPassWord) {
		Connection connection = null;
		
		try {
			//establish java JDBC Driver
			Class.forName(DatabaseFunction.JDBC_DRIVER);
			connection = DriverManager.getConnection(DatabaseFunction.JDBC_CONNECTION_URL, 
					adminUserName, adminPassWord);
			if(connection != null) {
				System.out.println("\nLogin is Successful");
			}else {
				System.out.println("\nConnection failed!");
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
	
	public ResultSet getRecords() {
		ResultSet records = null;
		
		try {
			Connection connection = getConnection();
			
			if(connection != null) {
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.SELECT_FROM_STUDENTS_INFO);
				
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
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.SELECT_SEARCH_STUDENT);
					
				pstmt.setString(1, student_id);
				records = pstmt.executeQuery();
				records.next();
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
					PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.DELETE_STUDENT);
					pstmt.setString(1, student_id);
					pstmt.executeUpdate();
				
				isSuccess = true;
				
				
			}catch(SQLException sqle) {
				System.err.println(sqle.getMessage());
				
			}
			
		
		
		return isSuccess;
	}
	
	public ResultSet reportGenerator(String username, String password, String course) {
		ResultSet records = null;
		
		try {
			Connection connection = getConnection();
			if(connection != null && username.equals(DatabaseFunction.JDBC_USERNAME)
					&& password.equals(DatabaseFunction.JDBC_PASSWORD)) {
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.SELECT_REPORT_GENERATOR);
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
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.DELETE_ALL_RECORDS);
				pstmt.executeUpdate();
				isSuccess = true;
			
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
		return isSuccess;
	}
	
	public boolean isDbEmpty(ResultSet records) {
		boolean isEmpty = false;
		try {
			if(records.next() == true) {
				isEmpty = true;
			}else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isEmpty;
	}
	public static void courseCounter(String course) {
		String actualCourse = course.toString();
		System.out.println(actualCourse);
		if(actualCourse.equals("BS CS")) {
			setCsStudents();
			
		}else if(actualCourse.equals("BS IT")) {
			setItStudents();
		}else if(actualCourse.equals("BS IS")) {
			setIsStudents();
		}
	}


}
