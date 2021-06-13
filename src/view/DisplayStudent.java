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
					System.out.println("Year Level: " + rs.getInt("yearLevel"));
					System.out.println("Units Enrolled: " + rs.getInt("unitsEnrolled"));
					System.out.println("");
					System.out.println("");
					System.out.println("");
				}
				System.out.println("......");
				System.out.println("Total Students Enrolled: ");
				System.out.println("");
				System.out.println("");
				System.out.println("Total # for CS:");
				System.out.println("Total # for IS:");
				System.out.println("Total # for IT:");
			
			} catch(SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
			
		}else {
			System.out.println("Database is empty.");
		}
	}
	
	public static void searchStudentOutput(ResultSet rs) {
		if(rs != null) {
			try {
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("Please wait.... searching for student record " + rs.getString("id"));
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("Record Found!");
				System.out.println("");
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
			
			} catch(SQLException sqle) {
				System.err.println(sqle.getMessage());
			}
			
		}else {
			System.out.println("Record does not exist.");
		}
		}
	
	public static boolean adminAuthentication(String userName, String userPassword) {
		boolean status = false;
		Connection conn = null;
		StudentBean studentObj = new StudentBean();
		
		conn = studentObj.adminConnection(userName, userPassword);
		if(conn != null) {
			status = true;
			System.out.println("Login Successful");
		}else {
			System.out.println("Login unsuccessful");
		}
		
		return status;
		
	}
	
	public static void searchDeleteStudentOutput(boolean result, String id) {
		System.out.println("Please wait.... searching for student record " + id);
		if(result) {
			System.out.println("Record found and successfully deleted!");
		}else {
			System.out.println("Record not found");
		}
		
	}


}