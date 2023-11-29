//package roles;
//
//public class Professor extends User {
//
//    
//	public Professor(String name, String id, String username, String password) {
//		this.id = id;
//		this.name = name;
//		this.username = username;
//		this.password = password;
//		this.userType = "Professor";
//
//	}
//	
//	public String getUsername() {
//		return this.username;
//	}
//	
//	public String getPassword() {
//		return this.password;
//	}
//
//    public String toString() {
//        return ("Id:" + id + "\nName:" + name +  "\nUsername:" + username + "\nPassword:" + password);
//    }
//
//	public String getName() {
//		return this.name;
//	}
//
//}


package roles;

import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;

public class Professor extends User {
	
	/**
	 * Calls constructor for superclass user and takes additional parameter "type"
	 * @param userName for User
	 * @param password for User
	 * @param name for User
	 * @param id for User
	 * @param courses ArrayList of all courses available
	 */
	public Professor(String userName, String password, String name, String id, ArrayList<Course> courses, ArrayList<Student> students, ArrayList<Professor> professors) {
		
		//Call constructor for superclass
		super(userName, password, name, id, courses, students, professors);
		
		//Set variable type to professor
		this.userType = "professor";
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
		System.out.println("1 -- View given courses");
		System.out.println("2 -- View student list of the given course");
		System.out.println("3 -- Return to previous menu");	
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
		String[] loginOptions = {"1", "2", "3"};
		
		//Prompt for user input
		String userInput = scan.nextLine();
		
		//Create boolean for returning to previous menu
		boolean goBack = false;
		
		//While user hasn't selected to back to previous menu
		while (goBack == false){
			
			//Print menu for list of actions
			this.printActionMenu();
		
			//While the user has not provided valid input (check by calling checkValidInput() method)
			while (this.checkValidInput(loginOptions, userInput) == false) {
				System.out.println("Please enter a valid response!");
				userInput = scan.nextLine();
			}
		
			//Call respective class method based on action selected by user
			//Return without calling method if 3 is selected
			if (userInput.equals("1")) {
				this.viewGivenCourses();
			
			} else if(userInput.equals("2")) {
				this.viewStudentList(scan);
			
			} else if(userInput.equals("3")) {
			}
		}
	}
	
	/**
	 * Displays all assigned courses for professor
	 */
	public void viewGivenCourses() {
		System.out.println("---------------The Course List----------------");
		for(Course each : courses) {
			//check for professor name
			if(each.getCourseInstructor().equals(this.getName())) {
				System.out.println(each);
			}
		}
	}
	
	/**
	 * Prompts for course & displays student list
	 */
	public void viewStudentList(Scanner s) {
		
		while(true) {
			//prompt input for class id
			System.out.println("Please enter the course ID, eg. 'CIS519.");
			System.out.println("Or type 'q' to quit");
			String inputString = s.next();
			
			//check that it isn't 'q'
			if(inputString.equals("q")) {
				continue ;
			}
				
			//look through all students and print it if they have it in hash map
			for(Student each : User.students) {
				
				if(each.Schedule.containsKey(inputString)) {			
					System.out.println(each.getId() + " " + each.getName());
				}
			}
		}
		
	}

}
