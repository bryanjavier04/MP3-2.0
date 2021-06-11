package controller;
import model.StudentBean;
import java.util.Scanner;

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
		System.out.println("Selection Option");
		userChoice = S.next().charAt(0);
		userChoice = Character.toUpperCase(userChoice);
	
		switch(userChoice) {
		case 'A':
			// Code here
			S = new Scanner(System.in);
			StudentBean enterStudent = new StudentBean();
			System.out.println("Enter Student ID: ");
			enterStudent.setStudentId(S.nextLine());
			System.out.println("Enter Student Last Name: ");
			enterStudent.setLastName(S.nextLine());
			System.out.println("Enter Student First Name: ");
			enterStudent.setFirstName(S.nextLine());
			//Concatenates to one full Name
			enterStudent.concatenateFirstNameLastName();
			System.out.println("Enter Student Course: ");
			enterStudent.setCourse(S.nextLine());
			System.out.println("Enter Student Year Level: ");
			enterStudent.setYear(S.nextInt());
			System.out.println("Enter Number of Units Enrolled: ");
			enterStudent.setUnits(S.nextInt());
			enterStudent.insertRecord();
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
