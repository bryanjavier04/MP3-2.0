package view;
import java.sql.*;
import utility.Security;
import model.StudentBean;
public class DisplayStudent {

		public static void listStudents(ResultSet rs) {
			System.out.println("Lists of Students Enrolled\r\n" + "=====================");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			if(rs !=null) {
				try {
					while(rs.next()) {

						System.out.println("ID: " + rs.getString("id"));
						System.out.println("Name: " + Security.decrypt(rs.getString("name")));
						System.out.println("Course: " + Security.decrypt(rs.getString("course")));
						
						//StudentBean.courseCounter(Security.decrypt(rs.getString("course")));
						
						System.out.println("Year Level: " + rs.getInt("yearLevel"));
						System.out.println("Units Enrolled: " + rs.getInt("unitsEnrolled"));
						System.out.println("");
						System.out.println("");
						System.out.println("");
					}
					System.out.println("......");
					System.out.println("Total Students Enrolled: " + StudentBean.totalStudents);
					System.out.println("");
					System.out.println("");
					System.out.println("Total # for CS:" + StudentBean.csStudents);
					System.out.println("Total # for IS:" + StudentBean.isStudents);
					System.out.println("Total # for IT:" + StudentBean.itStudents);
					System.out.println("");
					System.out.println("");
	

				} catch(SQLException sqle) {
					System.err.println(sqle.getMessage());
				}

			}else {
				System.out.println("Database is empty.");
			}
		}

		
		
		
		
	
	
	public static void searchStudentOutput(ResultSet rs, String id) {

	try {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Please wait.... searching for student record " +id);
		if(rs.next()) {
			System.out.println("");
			System.out.println("");
			System.out.println("Record Found!");
			System.out.println("");
			System.out.println("");
				System.out.println("ID: " + rs.getString("id"));
				System.out.println("Name: " + Security.decrypt(rs.getString("name")));
				System.out.println("Course: " + Security.decrypt(rs.getString("course")));
				System.out.println("Year Level: " + rs.getInt("yearLevel"));
				System.out.println("Units Enrolled: " + rs.getInt("unitsEnrolled"));
				System.out.println("");
				System.out.println("");
				System.out.println("");
		}else{
			System.err.println("Error on record search - Student ID " + id  + " not found!");
		}
	}catch(SQLException sqle) {
		System.err.println("Record does not exist.");
	}
		
		}
	
	public static boolean adminAuthentication(String userName, String userPassword) {
		boolean status = false;
		Connection conn = null;
		StudentBean studentObj = new StudentBean();
		conn = studentObj.adminConnection(userName, userPassword);
		if(conn != null) {
			status = true;
		}
		
		return status;
		
	}
	
	public static void searchDeleteStudentOutput(boolean result, String id) {
		System.out.println("\n\nPlease wait.... searching for student record " + id);
		if(result) {
			System.out.println("Record found and successfully deleted!");
		}else {
			System.err.println("Error on record search - Student ID " + id + " not found! Record cannot be deleted.");
		}
		
	}
	
	public static void reportGenerator(ResultSet rs, String course) {
		if(course.equals("BS CS") || course.equals("BS-CS")) {
			System.out.println("\n\nTotal number of "+ course + " student: " + StudentBean.csStudents);
		}else if(course.equals("BS IT") || course.equals("BS-IT")) {
			System.out.println("\n\nTotal number of "+ course + " student: " + StudentBean.itStudents);
		}else if(course.equals("BS IS") || course.equals("BS-IS")) {
			System.out.println("\n\nTotal number of "+ course + " student: " + StudentBean.isStudents);
		}else if (course.equals("ALL")){
			System.out.println("\n\nTotal number of students: " + StudentBean.totalStudents);
		}else {
			
		}
	
		System.out.println("\n\nList of Students Enrolled");
		System.out.println("=============================\n\n");
		
		if(rs !=null) {
			try {
				while(rs.next()) {

					System.out.println("ID: " + rs.getString("id"));
					System.out.println("Name: " + Security.decrypt(rs.getString("name")));
					System.out.println("Course: " + Security.decrypt(rs.getString("course")));
					
					//StudentBean.courseCounter(Security.decrypt(rs.getString("course")));
					
					System.out.println("Year Level: " + rs.getInt("yearLevel"));
					System.out.println("Units Enrolled: " + rs.getInt("unitsEnrolled"));
					System.out.println("");
					System.out.println("");
					System.out.println("");
				}


			} catch(SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
		}
		
	}
	
	public static void tablePurge() {
		StudentBean studObj = new StudentBean();
		System.out.println("");
		System.out.println("");
		System.out.println("Please wait. Deleting all records......");
		studObj.purgeRecords();
		System.out.println("\nALL records successfully deleted.");
	}
	public static void programTerminationOutput() {
		System.out.println("\n\nProgram terminated. Thank you for using the system.");
	}

	public static void existingIdDisplay(String id) {
		System.out.println("");
		System.out.println("");
		System.out.println("Error on record insert - there is already a student ID with " + id +". Listed are the details for student ID " + id +".");
		StudentBean existingIdCheck = new StudentBean();
		ResultSet existingEntry = existingIdCheck.searchStudent(id);
		System.out.println("");
		System.out.println("");
		//
		try {
			
			//changed from "existingEntry != null" to "existingEntry.next()"
			if(existingEntry.next()) {
				System.out.println("ID: " + existingEntry.getString("id"));
				System.out.println("Name: " + Security.decrypt(existingEntry.getString("name")));
				System.out.println("Course: " + Security.decrypt(existingEntry.getString("course")) );
				System.out.println("Year Level: " + existingEntry.getInt("yearLevel"));
				System.out.println("Units Enrolled: " + existingEntry.getInt("unitsEnrolled"));
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		
	}
	

}