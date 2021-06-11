package controller;

import model.StudentBean;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccessStudentDB {

	public static void main(String[] args) {
		Scanner userIn = new Scanner(System.in);
		StudentBean student = new StudentBean();
		String choice = "";
		System.out.println("[A]-dd Student");
		System.out.println("[L]-ist Students");
		System.out.println("[S]-earch Student");
		System.out.println("[D]-elete Student");
		System.out.println("[R]-eport Generator");
		System.out.println("[P]-urge");		
		System.out.println("[Q]-uit");
		System.out.println("");
		System.out.print("Selection Option: ");
		choice = userIn.nextLine();
		
		switch(choice) {
		
			case "A":
				try {
					String studentId = "";
					String lastName = "";
					String firstName = "";
					String course = "";
					int year = 0;
					int units = 0;
					
					System.out.print("Enter Student ID: ");
					studentId = userIn.nextLine();
					System.out.print("Enter Lastname: ");
					lastName = userIn.nextLine();
					System.out.print("Enter Firstname: ");
					firstName = userIn.nextLine();
					System.out.print("Enter Course: ");
					course = userIn.nextLine();
					System.out.print("Enter Year: ");
					year = userIn.nextInt();
					System.out.print("Enter Units: ");
					units = userIn.nextInt();
					
					student.setStudentId(studentId);
					student.setLastName(lastName);
					student.setFirstName(firstName);
					student.setCourse(course);
					student.setYear(year);
					student.setUnits(units);
					
					if(student.insertRecord()){
						System.out.println("\nStudent Successfully Added!");
					}else {
						System.out.println("\nAn Error Occurred.");
					}
					
					break;
					
				}catch(InputMismatchException ime) {
					System.err.println("\nInvalid Entry! Please Enter An Integer.");
				}catch(Exception e) {
					System.err.println("\nAn Unknown Error Occurred.");
				}
				
		
		}
		
	}

}
