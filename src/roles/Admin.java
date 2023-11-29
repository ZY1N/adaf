package roles;

import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;

public class Admin extends User {

	/**
	 * Calls constructor for superclass user and takes additional parameter "type" and ArrayList Users
	 * @param userName for User
	 * @param password for User
	 * @param name for User
	 * @param id for User
	 * @param courses ArrayList of all courses available
	 * @param usersLst ArrayList of all users in system
	 */
	public Admin(String userName, String password, String name, String id, ArrayList<Course> courses, ArrayList<Student> students, ArrayList<Professor> professors) {
		
		//Call constructor in superclass User
		super(userName, password, name, id, courses, students, professors);
		
		//why??
//		//Create arraylist users for modification of passed usersLst by reference
//		ArrayList<User> users = usersLst;
		
		//Set variable type to admin
		this.userType = "admin";
	}

	/**
	 * Prints menu of action items for given subclass of User
	 * Must implement to override abstract method
	 */
	@Override
	public void printActionMenu() {
		
		//Print Action menu message
		System.out.println("--------------------------------");
		System.out.println("Welcome, " + this.getName());
		System.out.println("--------------------------------");
		System.out.println("1 -- View all courses");
		System.out.println("2 -- Add new courses");
		System.out.println("3 -- Delete courses");
		System.out.println("4 -- Add new professor");
		System.out.println("5 -- Delete professor");
		System.out.println("6 -- Add new student");
		System.out.println("7 -- Delete student");
		System.out.println("8 -- Return to previous menu");	
		System.out.println("");
		System.out.println("Please enter your option, eg. '1'.");
		System.out.println("");


	}

	/**
	 * Prompts for user input and performs action based on given list of actions for subclass of User
	 */
	@Override
	public void performAction(Scanner scan) {
		
		//Create array of valid user actions
		String[] loginOptions = {"1", "2", "3", "4", "5", "6", "7", "8"};
		
		
		//Create boolean for returning to previous menu
		boolean goBack = false;
		
		//While user hasn't selected to back to previous menu
		while (goBack == false){
			
			//Print menu for list of actions
			this.printActionMenu();

			//Prompt for user input
			String userInput = scan.nextLine();
		
			//While the user has not provided valid input (check by calling checkValidInput() method)
			while (this.checkValidInput(loginOptions, userInput) == false) {
				System.out.println("Please enter a valid response!");
				userInput = scan.nextLine();
			}
		
			//Call respective class method based on action selected by user
			//Return without calling method if 8 is selected
			if (userInput.equals("1")) {
				this.viewAllCourses();
			
			} else if(userInput.equals("2")) {
				this.addNewCourse();
			
			} else if(userInput.equals("3")) {
				this.deleteCourse();
			
			} else if(userInput.equals("4")) {
				this.addProfessor();
			
			} else if(userInput.equals("5")) {
				this.deleteProfessor();
			
			} else if(userInput.equals("6")) {
				this.addStudent();
			
			} else if(userInput.equals("7")) {
				this.deleteStudent();
			
			} else if(userInput.equals("8")) {
				goBack = true;
			}
		}
	}
	
	/**
	 * Displays a list of all courses in management system
	 */
	public void viewAllCourses() {		
		
		//Iterate over ArrayList of courses and print
		for(Course c : courses) {
			System.out.println(c);
		}		
	}
	
	/**
	 * Adds new course to courses ArrayList
	 */
	public void addNewCourse() {
		
		
		
	}
	
	/**
	 * Removes course from courses ArrayList
	 */
	public void deleteCourse() {
		
	}
	
	/**
	 * Adds new professor to user ArrayList
	 */
	public void addProfessor() {
		
	}
	
	/**
	 * Remove professor from user ArrayList
	 */
	public void deleteProfessor() {
		
	}
	
	/**
	 * Add student to arrayList
	 */
	public void addStudent() {
		
	}
	
	/**
	 * Remove student from arrayList
	 */
	public void deleteStudent() {
		
	}

}
