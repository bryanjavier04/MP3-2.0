package controller;
import model.StudentBean;
import view.DisplayStudent;
import java.sql.*;
import java.util.Scanner;

public class AccessStudentDB {

	public static void main(String[] args) {
		//initialize StudentBean student counter
		StudentBean studentCount = new StudentBean();
		studentCount.updateStudentCounter();
		String studentID;
		String choice = "Y";
		String choiceOfUser;
		Scanner userIn = new Scanner(System.in);
		while(choice.equals("Y")){
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
			choiceOfUser = userIn.nextLine();
		
			switch(choiceOfUser) {
				case "A":
					Scanner addStudent = new Scanner(System.in);
					StudentBean enterInfo = new StudentBean();
					
					System.out.print("\n\nEnter Student ID: ");
					enterInfo.setStudentId(addStudent.nextLine());
					System.out.print("Enter Student Last Name: ");
					enterInfo.setLastName(addStudent.nextLine());
					System.out.print("Enter Student First Name: ");
					enterInfo.setFirstName(addStudent.nextLine());
			
					enterInfo.concatenateFirstNameLastName();
					System.out.print("Enter Student Course: ");
					enterInfo.setCourse(addStudent.nextLine());
					System.out.print("Enter Student Year Level: ");
					enterInfo.setYear(addStudent.nextInt());
					System.out.print("Enter Number of Units Enrolled: ");
					enterInfo.setUnits(addStudent.nextInt());
						
					
					if(enterInfo.insertRecord()) {
						System.out.println("\nRecord successfully inserted.");
					}else {
						System.out.println("\nRecord not saved");
					}
						
					break;
				
				case "L":
					ResultSet records = new StudentBean().getRecords();
					records = new StudentBean().getRecords();
					DisplayStudent.listStudents(records);
						
					break;
			case "S":
				S = new Scanner(System.in);
				
				records = new StudentBean().getRecords();
				if(studentObject.isDbEmpty(records)) {
					System.out.print("Enter Student ID: ");
					studentID = S.nextLine();
					records = new StudentBean().searchStudent(studentID);
					DisplayStudent.searchStudentOutput(records, studentID);
				}else {
					System.out.println("\nDatabase is empty.");
				}
				
				
				break;
			case 'D':
				
				S = new Scanner(System.in);
				System.out.print("\n\nEnter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					System.out.println("\n\nLogin successful!");
					
					records = new StudentBean().getRecords();
					if(studentObject.isDbEmpty(records)) {
						System.out.print("\n\nEnter Student ID: ");
						studentID = S.nextLine();
						result = new StudentBean().deleteStudent(studentID);
						DisplayStudent.searchDeleteStudentOutput(result, studentID);
					}else {
						System.out.println("\nDatabase is empty.");
					}
					
				}
				break;
				
			case 'R':
				String course;
				S = new Scanner(System.in);
				System.out.print("\n\nEnter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					records = new StudentBean().getRecords();
					if(studentObject.isDbEmpty(records)) {
						studentCount.updateStudentCounter();
						System.out.print("\n\nReport Generator Facility ");
						System.out.print("\n\nEnter Course Code: ");
						course = S.nextLine();
						records = new StudentBean().reportGenerator(course);
						DisplayStudent.reportGenerator(records, course);
					}else {
						System.out.println("\nDatabase is empty.");
					}
				}
				break;

			case 'P':
				S = new Scanner(System.in);
				System.out.print("\n\nEnter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					records = new StudentBean().getRecords();
					if(studentObject.isDbEmpty(records)) {
						System.out.print("\n\nAre you sure you want to purge all records [Y/N]?: " );
						userChoice = S.next().charAt(0);
						userChoice = Character.toUpperCase(userChoice);
						if(userChoice == 'Y') {
							DisplayStudent.tablePurge();
						}
					}else {
						System.out.println("\nDatabase is empty.");
					}
				}
					break;

			case 'Q':
				
				S = new Scanner(System.in);
				System.out.print("\n\nEnter administrator account: ");
				userName = S.nextLine();
				System.out.print("Enter administrator password: ");
				userPassword = S.nextLine();
				if(DisplayStudent.adminAuthentication(userName, userPassword)) {
					//fixed format
					System.out.print("\n\nAre you sure you want to terminate the application[Y/N]?: " );
					choice = S.nextLine();
				}
				   break;
			   
			default:
				System.err.println("INVALID CHOICE! TRY AGAIN.");
				break;
		}


		}
			DisplayStudent.programTerminationOutput();
	}
		
}
