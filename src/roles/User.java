//package roles;
//
//public abstract class User {
//
//	 public String id;
//	 public String name;
//	 public String username;
//	 public String password;
//	 public String userType;
//
//
//	public User() {
//		// TODO Auto-generated constructor stub
//	}
//
//}

//	 public String id;
//	 public String name;
//	 public String username;
//	 public String password;
//	 public String userType;


package roles;

import java.util.ArrayList;
import java.util.Scanner;

import courses.*;

/**
 * Abstract class User represents a generic (or abstract) user
 */
public abstract class User {

	//instance variables
	
	/**
	 * ID for user login.
	 * Unique for all users.
	 */
	private String username;
	
	/**
	 * Password for user login.
	 */
	private String password;
	
	/**
	 * Actual name of user.
	 * Utilized for display, and in some cases identification.
	 */
	private String name;
	
	/**
	 * Internal ID for user.
	 * Unique for each subclass of User (i.e. 001 for Student & 001 for Professor are different)
	 */
	private String id;
	
	/**
	 * Notes the type of User object.
	 * Set as "default" by default
	 */
	String userType = "default";
	
	/**
	 * Arraylist of courses to pass to User object
	 */
	static  ArrayList<Course> courses;
	static  ArrayList<Student> students;
	static  ArrayList<Professor> professors;


	
	//Constructor
	/**
	 * Called by subclasses of User to create instances of different types of users.
	 * @param userName for user login.
	 * @param password for user login.
	 */
	public User (String userName, String password, String name, String id, ArrayList<Course> courses, ArrayList<Student> students, ArrayList<Professor> professors) {
		
		//initialize instance variables
		this.username = userName;
		this.password = password;
		this.name = name;
		this.id = id;
		User.courses = courses;
		User.students = students;
		User.professors = professors;
	}
	
	//Methods
	
	/**
	 * Checks if given password is correct for User object
	 * @param inputPassword to check
	 * @return true if password correct, otherwise false
	 */
	public boolean checkPassword(String inputPassword) {
		
		//Check if given password is equal to inputPassowrd
		return this.getPassword().equals(inputPassword);
	}
	
	/**
	 * Returns user info in string format
	 */
	public String toString() {
		
		return ("Type: " + this.getType() + " |Username : " + this.getUserName() + 
				" |Name: " + this.getName() + " |ID: " + this.getId());
	}
	
	/**
	 * Helper method checks if user input is valid based on given array of options & given user input
	 * @param options for valid input
	 * @param userInput to check
	 * @return true if user input is within options, otherwise false
	 */
	public boolean checkValidInput(String[] options, String userInput) {
		
		//Iterate over list of options
		for (String o: options) {
			//If userInput in list, return true
			if (o.equals(userInput)) {
				return true;
			}
		}
		//Otherwise return false
		return false;
	}
	
	//Abstract methods
	
	/**
	 * Prints list of potential actions for User.
	 * All subclasses of User MUST implement this method.
	 */
	public abstract void printActionMenu();
	
	/**
	 * Performs an action from the given list of actions on the Action Menu based on user input.
	 * @param action
	 */
	public abstract void performAction(Scanner scan);
	
	//Getters methods
	
	/**
	 * Get username
	 * @return string userName
	 */
	public String getUserName() {
		return username;
	}
	
	/**
	 * Get password
	 * @return string password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Get name
	 * @return string name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get ID
	 * @return ID
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get User type
	 * @return user type
	 */
	public String getType() {
		return userType;
	}
	
}
