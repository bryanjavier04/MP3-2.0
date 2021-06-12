package controller;
import model.StudentBean;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AccessStudentDB {

	public static void main(String[] args) {
		Scanner S = new Scanner(System.in);
		char userChoice = ' ';
		
		System.out.println("[A]-dd Student");
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
				enterStudent.insertRecord();
				
				
			}catch(InputMismatchException ime) {
				System.err.println("\nInvalid Input" + ime.getMessage());
			}catch(Exception e) {
				System.err.println("An Unknown Error Occurred.");
			}
			break;
			
		case 'L':
			// Code here
			break;
		case 'S':
			// Code here
			break;
			
		case 'D':
			// Code here
			break;
			
		case 'R':
			// Code here
				break;

		case 'P':
			// Code here
				break;

		case 'Q':
			// Code here
			   break;
		   
		default:
			// Code here
	}


	}
}
