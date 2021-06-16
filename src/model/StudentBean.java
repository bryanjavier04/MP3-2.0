package model;

import java.io.Serializable;
import utility.*;
import view.DisplayStudent;

import java.sql.*;
import exception.InvalidCourseException;

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

	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
	
			this.studentId = studentId;
		
		
	}

	public String getLastName() {
		return lastName;
	}

	//changes made: encryption removed due to output error
	public void setLastName(String lastName) {
		lastName += ", ";
		lastName = capitalize(lastName);
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	//changes made: encryption removed due to output error
	public void setFirstName(String firstName) {
		firstName = capitalize(firstName);
		this.firstName = firstName;
	}
	
	//changes made: encryption of firstName and lastName moved here
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
		Security.decrypt(course);
		return course;
	}

	public void setCourse(String course) {
		if(course.equals("BS CS") || course.equals("BS IT") || course.equals("BS IS")) {
			this.course = Security.encrypt(course);
		}else {
			throw new InvalidCourseException();
		}
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
				//format fixed
				System.out.println("\nLogin Successful");
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
		Connection connection = getConnection();
		try {
			if(connection != null) {
				//transaction implementation
				connection.setAutoCommit(false);
				
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.INSERT_INTO_STUDENTS_INFO);
				pstmt.setString(1, this.studentId);
				pstmt.setString(2, this.fullName);
				pstmt.setString(3, this.course);
				pstmt.setInt(4, this.year);
				pstmt.setInt(5, this.units);
				
				pstmt.executeUpdate();
				connection.commit();
				isSuccess = true;
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			try {
				//in an event of an error this will roll back the commit
				connection.rollback();
			}catch(SQLException sqleCom) {
				System.err.println(sqleCom.getMessage());
			}
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
			getCourseCount(course);
			Connection connection = getConnection();
			
			if(course.equals("ALL")) {
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.SELECT_REPORT_GENERATOR_ALL);
					
				records = pstmt.executeQuery();
			}else if(course.equals("BS CS") || course.equals("BS IT") || course.equals("BS IS")){
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.SELECT_REPORT_GENERATOR);
				pstmt.setString(1, Security.encrypt(course));
					
				records = pstmt.executeQuery();
			}else {
				throw new InvalidCourseException();
			}
			
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}catch(InvalidCourseException ice) {
			System.err.println("Invalid Course Code! Try Again.");
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
	
	public void getCourseCount(String course) {
		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.COUNT_COURSE_RECORDS);
			pstmt.setString(1, Security.encrypt(course));
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				switch(course) {
					case "BS CS":
						StudentBean.csStudents = rs.getInt("count");
						break;
				
				}
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
	}
	
	public void updateStudentCounter() {
		Connection connection = getConnection();
		StudentBean.csStudents = 0;
		StudentBean.itStudents = 0;
		StudentBean.isStudents = 0;
		try {
			if(connection != null) {
				PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.SELECT_COURSE_COLUMN);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					courseCounter(Security.decrypt(rs.getString("course")));
				}
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
	}
	
	public boolean uniqueIdFinder(String id) {
		boolean recordExists = false;
		ResultSet rs = null;
		try {
			Connection connection = getConnection();
			PreparedStatement pstmt = connection.prepareStatement(DatabaseFunction.COUNT_ID_RECORDS);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt("idcount");
				if(count > 0) {
					recordExists = true;
				}
			}
				
		}catch(SQLException sqle){
			System.err.println(sqle.getMessage());
		}
		
		return recordExists;
		
	}


}
