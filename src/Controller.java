import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import courses.*;
import files.*;
import roles.*;


public class Controller {
	
	public static void printMenu() {
		System.out.println("--------------------------------");
		System.out.println("Students Management System");
		System.out.println("--------------------------------");
		System.out.println("1 -- Login as a student");
		System.out.println("2 -- Login as a professor");
		System.out.println("3 -- Login as a admin");
		System.out.println("4 -- Login as a system\n");
		System.out.println("Please enter your option, eg. '1'.");
		
		return;
	}
	
	public static void printStudentMenu(String name) {
		System.out.println("--------------------------------");
		System.out.println("Welcome, " + name);
		System.out.println("--------------------------------");
		System.out.println("1 -- View all courses");
		System.out.println("2 -- Add courses to your list");
		System.out.println("3 -- View enrolled courses");
		System.out.println("4 -- Drop courses in your list");
		System.out.println("5 -- View grades");
		System.out.println("6 -- Return to previous menu");
		System.out.println("Please enter your option, eg. '1'.");	
	}
	
	public static void printProfessorMenu(String name) {
		System.out.println("--------------------------------");
		System.out.println("Welcome, " + name);
		System.out.println("--------------------------------");
		System.out.println("1 -- View given courses");
		System.out.println("2 -- View student list of the given course");
		System.out.println("3 -- Return to previous menu");
		System.out.println("Please enter your option, eg. '1'.");	
	}

	
	public static int getMenuInput(int min, int max, Scanner s) {
		//loop until get a response 1-6
		int input = 0;

		//loop though input is valid
		while(true) {		
			//make sure that an int is entered
			try {				
				input = s.nextInt();

				//check to see if int is 1-4
				if(input >= min && input <= max) {
					break;
				}
				else {
					System.out.println("Please enter a valid int (1-" + max + ").");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter a valid int (1-" + max + ").");
				s.nextLine();
			}
		}
		
		return(input);
	}
	
	public static User signIn(int signInFlag, ArrayList <Admin> adminList, ArrayList <Professor> professorList, ArrayList <Student> studentList, Scanner s) {
		//return user after prompting sign in
		String username;
		String password;
		
		//used to compared to given when iterating
		String tempUsername;
		String tempPassword;
		
		while(true) {
			System.out.println("Please enter username.");
			username = s.next();
			
//			System.out.print(username);
			System.out.println("Please enter password.");
			password = s.next();
			
			//check validity
			if(signInFlag == 1) {
				//check student arraylist
				
				for(Student temp : studentList) {
					tempUsername = temp.getUsername();
					tempPassword = temp.getPassword();
					
//					System.out.println("|"+username + "| : |" + tempUsername+"|");
//					System.out.println("|"+password + "| : |" + tempPassword+"|");
					//System.out.println(username.equals(tempUsername));


					if(tempUsername.equals(username) && tempPassword.equals(password)) {
						return(temp);
					}
				}
				
			}
			else if(signInFlag == 2) {
				//check professor
				
				for(Professor temp : professorList) {
					tempUsername = temp.getUsername();
					tempPassword = temp.getPassword();
					
					if(tempUsername.equals(username) && tempPassword.equals(password)) {
						return(temp);
					}
				}
			}
			else if(signInFlag == 3) {
				//check other
				for(Admin temp : adminList) {
					tempUsername = temp.getUsername();
					tempPassword = temp.getPassword();
					
					if(tempUsername.equals(username) && tempPassword.equals(password)) {
						return(temp);
					}
				
				}
			}
			System.out.println("Invalid combo. Try again.");
		} 
		//return null;
		
	}
	
	public static void main(String [] args) {
		
		//load the material
		ArrayList <Course> courseList = FileInfoReader.readCourse();
		ArrayList <Admin> adminList = FileInfoReader.readAdmin();
		ArrayList <Professor> professorList = FileInfoReader.readProfessor();
		ArrayList <Student> studentList = FileInfoReader.readStudent(courseList);



		
		//holds int to menu choosing role of sign in 
		int signInFlag = 1;
		
		
		//login variables
		User user = null;
		
		//DO NOT MESS WITH THIS
		//USING SCANNER IN MULTIPLE AREAS, IF CLOSED IN ONE IT WILL SHUTDOWN SYS.IN, AND THERE IS NO WAY TO REOPEN
		//JUST PASS THIS ONE ALONG AS VARIABLE AND CLOSE IT AT THE END AT ONCE
		Scanner s = new Scanner(System.in);
		
		
		int mainMenuFlag = 1;
		int inputFlag = 0;
		
		String inputString;
		
		while(true) {
			
			if(mainMenuFlag == 1) {
				//print the start elements
				printMenu();
				//get signin input for role
				signInFlag = getMenuInput(1, 4, s);
				mainMenuFlag = 0;
			}
			
			
			//push it into signin function
			user = signIn(signInFlag, adminList, professorList, studentList, s); 
			
			//System.out.print(user.userType);
			
			//enter student
			if(signInFlag == 1) {
				//print menu and wait for input
				printStudentMenu(((Student)user).getName());
				inputFlag = getMenuInput(1, 6, s);
				
				/*=======break them into functions======*/
				
				//show all courses
				if(inputFlag == 1) {
					for(Course each : courseList) {
						System.out.println(each);
					}
				}
				//sign up courses 1 hour
				else if(inputFlag == 2) {
					//add course
					
					inputString = s.next();
					
					
					//go back to previous menu
					if(inputString.equals("q")) {
						continue;
					}
					
					//check to see if course exists
					
					//if it exists add to student hashmap
					//then add 1 to course
					
					
				}
				//view selected courses
				else if(inputFlag == 3) {
					((Student)user).printSelectedCourses();
					
					
				}
				//drop course 15 min
//				else if(inputFlag == 4) {
//					
//				}

				else if(inputFlag == 5) {
					((Student)user).printGrades();
				}
				//go back to main menu
				else if(inputFlag == 6) {
					continue ;
				}
				
			} 
			

			//enter professor
			else if(signInFlag == 2) {
				printProfessorMenu(((Professor)user).getName());
				inputFlag = getMenuInput(1, 3, s);
				
				if (inputFlag == 1) {
					System.out.println("---------------The Course List----------------");
					for(Course each : courseList) {
						//check for professor name
						if(each.getCourseInstructor().equals(((Professor)user).getName())) {
							System.out.println(each);
						}
					}
				}
				//get students for a class
				else if(inputFlag == 2) {
					
//					while(true){
						//prompt input for class id
						System.out.println("Please enter the course ID, eg. 'CIS519.");
						System.out.println("Or type 'q' to quit");
						inputString = s.next();
						
						//check that it isn't 'q'
						if(inputString.equals("q")) {
							continue;
						}
						
						//look through all students and print it if they have it in hash map
						for(Student each : studentList) {
							
							if(each.Schedule.containsKey(inputString)) {			
								System.out.println(each.getId() + " " + each.getName());
							}
//						}
						
					}
					
				}
				else if(inputFlag == 3) {
					continue;
				}
			}
			
			/*
//			//enter admin
//			elif(signInFlag == 3) {
//				printProfessorMenu();
//				inputFlag = getMenuInput(1, 8);
//				
//			}
//			//exit system
//			elif(signInFlag == 4) {
//				break;
//			} */
			
			
			break ;
		}
		//close scanner
		s.close();

	}
}
