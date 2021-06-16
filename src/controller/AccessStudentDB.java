package controller;
import model.StudentBean;
import view.DisplayStudent;
import java.sql.*;
import java.util.Scanner;

import exception.InvalidCourseException;

import java.util.InputMismatchException;

public class AccessStudentDB {

	public static void main(String[] args) {
		//initialize StudentBean student counter
		StudentBean studentCount = new StudentBean();
		studentCount.updateStudentCounter();
		String studentID;
		boolean accessRepeat = true;
		while(accessRepeat){
			StudentBean studentObject = new StudentBean();
			
			ResultSet records = null;
			Scanner S = new Scanner(System.in);
			boolean result = false;
			char userChoice = ' ';
			String userName;
			String userPassword;
			
			//fixed format
			System.out.println("\nIACADEMY");
			System.out.println("Student Registration Management System");
			System.out.println("\n[A]-dd Student");
			System.out.println("[L]-ist Students");
			System.out.println("[S]-earch Student");
			System.out.println("[D]-elete Student");
			System.out.println("[R]-eport Generator");
			System.out.println("[P]-urge");		
			System.out.println("[Q]-uit");
			System.out.println("");

			System.out.print("Selection Option: ");
			userChoice = S.next().charAt(0);
			userChoice = Character.toUpperCase(userChoice);
		
			switch(userChoice) {
			case 'A':
				// Code here
				try {
					S = new Scanner(System.in);
					StudentBean enterStudent = new StudentBean();
					System.out.print("Enter Student ID: ");
					enterStudent.setStudentId(S.nextLine());
					System.out.print("Enter Student Last Name: ");
					enterStudent.setLastName(S.nextLine());
					System.out.print("Enter Student First Name: ");
					enterStudent.setFirstName(S.nextLine());
					//Concatenates to one full Name
					enterStudent.concatenateFirstNameLastName();
					System.out.print("Enter Student Course: ");
					enterStudent.setCourse(S.nextLine());
					System.out.print("Enter Student Year Level: ");
					enterStudent.setYear(S.nextInt());
					System.out.print("Enter Number of Units Enrolled: ");
					enterStudent.setUnits(S.nextInt());
					
					if(enterStudent.uniqueIdFinder(enterStudent.getStudentId())) {
						System.out.println(enterStudent.uniqueIdFinder(enterStudent.getStudentId()));
						DisplayStudent.existingIdDisplay(enterStudent.getStudentId());
					}else {
						if(enterStudent.insertRecord()) {
							System.out.println("\nRecord Successfully Inserted!");
						}else {
							System.out.println("\nRecord Not Saved Due To Error!");
						}
						
					}
				
					
					
				}catch(InputMismatchException ime) {
					System.err.println("Invalid Input!, Please Enter An Integer\n");
				}catch(InvalidCourseException ice) {
					System.err.println(ice.getMessage());
				}catch(Exception e) {
					System.err.println("An Unknown Error Occurred.\n");
				}
				break;
				
			case 'L':
				records = new StudentBean().getRecords();
				if(studentObject.isDbEmpty(records)) {
					studentCount.updateStudentCounter();
					records = new StudentBean().getRecords();
					DisplayStudent.listStudents(records);
				}else {
					System.out.println("\nDatabase is Empty");
				}
				
				break;
			case 'S':
				S = new Scanner(System.in);
				
				System.out.print("Enter Student ID: ");
				studentID = S.nextLine();
				records = new StudentBean().searchStudent(studentID);
				DisplayStudent.searchStudentOutput(records, studentID);
				break;
			case 'D':
				
				S = new Scanner(System.in);
				System.out.print("Enter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					System.out.print("Enter Student ID: ");
					studentID = S.nextLine();
					result = new StudentBean().deleteStudent(studentID);
					DisplayStudent.searchDeleteStudentOutput(result, studentID);
				}
				break;
				
			case 'R':
				String course;
				S = new Scanner(System.in);
				System.out.print("Enter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					studentCount.updateStudentCounter();
					System.out.print("Enter Course Code: ");
					course = S.nextLine();
					records = new StudentBean().reportGenerator(course);
					DisplayStudent.reportGenerator(records, course);
				}
				break;

			case 'P':
				S = new Scanner(System.in);
				System.out.print("Enter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					//fixed format
					System.out.print("Are you sure you want to purge all records [Y/N]?: " );
					userChoice = S.next().charAt(0);
					userChoice = Character.toUpperCase(userChoice);
					if(userChoice == 'Y') {
						DisplayStudent.tablePurge();
					}
				}
					break;

			case 'Q':
				
				S = new Scanner(System.in);
				System.out.print("Enter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					//fixed format
					System.out.print("Are you sure you want to terminate the application[Y/N]?: " );
					userChoice = S.next().charAt(0);
					userChoice = Character.toUpperCase(userChoice);
					if(userChoice == 'Y') {
						accessRepeat = false;
						DisplayStudent.programTerminationOutput();
					}
				}
				   break;
			   
			default:
				System.err.println("INVALID CHOICE! TRY AGAIN.");
				break;
		}


		}
		
		System.out.println("\nProgram terminated. Thank you for using the system.");
	}
		
}
