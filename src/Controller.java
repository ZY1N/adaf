import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import courses.*;
import files.*;
import roles.*;


public class Controller {
	
	private static boolean checkMaxCapacity(int a, int b) {
		
		if(a <= b) {
			System.out.println(a + " : " + b);

			System.out.println("Can't add course. Max capacity reached.");
			return true;
		}
		return false;
	}
	
	private static int convertTimeInt(String time) {
		int timeInt;
		
		//break into two
		timeInt = Integer.parseInt(time.split(":")[0])*100 + Integer.parseInt(time.split(":")[1]);
		
		return(timeInt);
		
	}
	
	private static boolean checkTimeConflict(String days, String timeStart, String timeEnd, HashMap<String, Course> Schedule) {
		boolean timeConflict = false;
		
		//turn string into time
		int timeStartInt = convertTimeInt(timeStart);
		int timeEndInt = convertTimeInt(timeEnd);
		
		int timeStartIntComp;
		int timeEndIntComp;
		
		//compare day letter by letter
		//days.contains(char)
		
		
		//iterate through schedule
		for (Course tempCourse : Schedule.values()) {
			
			//iterate through days string
		      for (int i = 0; i < days.length(); i++) {
		    	  timeStartIntComp = convertTimeInt(tempCourse.getTimeStart());
		    	  timeEndIntComp = convertTimeInt(tempCourse.getTimeEnd());
		    	  
		    	  if(tempCourse.getDays().contains(Character.toString(days.charAt(i)))) {
		    		  //check to see if is within interval
		    		  
		    		  
		    		  if(timeStartIntComp <= timeStartInt || timeEndIntComp >= timeEndInt) {
		    			  //flip boolean
		    			  timeConflict = true;
		    			  System.out.println("The course you selected has time conflict with " + tempCourse.getCourseCode() + " " + tempCourse.getCourseName());
		    			  break ;
		    		  }
		    		  
		    	  }
		      }
		}
		
		return timeConflict;
	}
	
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
	
	public static void printAdminMenu(String name) {
		System.out.println("--------------------------------");
		System.out.println("Welcome, " + name);
		System.out.println("--------------------------------");
		System.out.println("1 -- View all courses");
		System.out.println("2 -- Add new courses");
		System.out.println("3 -- Delete courses");
		System.out.println("4 -- Add new professor");
		System.out.println("5 -- Delete professor");
		System.out.println("6 -- Add new student");
		System.out.println("7 -- Delete student");
		System.out.println("8 -- Return to previous menu");
		System.out.println("Please enter your option, eg. '1'.");
		
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
			
			System.out.println("Please enter password.");
			password = s.next();
			
			//check validity
			if(signInFlag == 1) {
				//check student arraylist
				for(Student temp : studentList) {
					tempUsername = temp.getUsername();
					tempPassword = temp.getPassword();
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
						
			//enter student
			if(signInFlag == 1) {
				//loop to keep up the student menu
				while(true) {
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
						while(true) {
							inputString = s.next();
							boolean courseExists = false;
							boolean timeConflict = false;
							boolean maxCapacityReached = false;
							Course tempCourse = null;
							System.out.println("Enter course ID");
	
							//go back to previous menu
							if(inputString.equals("q")) {
								break;
							}
							
							//check to see if course exists
							for(Course each : courseList) {
								if(each.getCourseCode().equals(inputString)) {
									courseExists = true;
									tempCourse = each;
								}
							}
	
							//course doesn't exist
							if(courseExists == false) {
								System.out.println("Error, course doesn't exist.");
								continue;
							}
							
							//check to see if it already exists in hashmap
							if(!((Student)user).Schedule.containsKey(inputString)){
								//check for time conflicts
								timeConflict = checkTimeConflict(tempCourse.getDays(), tempCourse.getTimeStart(), tempCourse.getTimeEnd(), ((Student)user).Schedule);
								maxCapacityReached = checkMaxCapacity(tempCourse.getCourseCapacity(), tempCourse.getStudentNum());
								
								//System.out.println(maxCapacityReached +" : " + timeConflict);
								//if there is no conflict then okay it
								if(!timeConflict && !maxCapacityReached) {
		
									//System.out.println(inputString);

									//if it exists add to student hashmap
									((Student)user).Schedule.put(inputString, tempCourse);	
									
									//add 1 to course
									tempCourse.addStudent();
									break;
								}
							}
							else {
								
								System.out.println("The course you want is already in your list");
							}
						}

						
					}
					//view selected courses
					else if(inputFlag == 3) {
						((Student)user).printSelectedCourses();
					}
					//drop course 15 min
					else if(inputFlag == 4) {
						
						inputString = s.next();
						
						//search for course
						((Student)user).Schedule.remove(inputString);						
					}
	
					else if(inputFlag == 5) {
						((Student)user).printGrades();
					}
					//go back to main menu
					else if(inputFlag == 6) {
						break ;
					}	
				}
			} 
			//enter professor
			else if(signInFlag == 2) {
				
				while(true) {
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
						
						//prompt input for class id
						System.out.println("Please enter the course ID, eg. 'CIS519.");
						System.out.println("Or type 'q' to quit");
						inputString = s.next();
						
						//check that it isn't 'q'
						if(inputString.equals("q")) {
							continue ;
						}
							
						//look through all students and print it if they have it in hash map
						for(Student each : studentList) {
							
							if(each.Schedule.containsKey(inputString)) {			
								System.out.println(each.getId() + " " + each.getName());
							}
						}
					}
					else if(inputFlag == 3) {
						break;
					}
				}
				
			}
			//enter admin
			else if(signInFlag == 3) {
				
				while(true) {
					printAdminMenu(((Admin)user).getName());
					inputFlag = getMenuInput(1, 8, s);
					
					//view all courses
					if(inputFlag == 1) {
						for(Course each : courseList) {
							System.out.println(each);
						}
					}
					//add new courses
					else if(inputFlag == 2) {
						System.out.println("");
						String newCourse;
						System.out.println("");
						String newName;
						System.out.println("");
						String newStart;
						System.out.println("");
						String newEnd;
						System.out.println("");
						String newDays;
						System.out.println("");
						String newCapacity;
						System.out.println("");
						String newLecturerID;
						System.out.println("");

						
					}
					
	//				else if(inputFlag == 3) {}
	//				else if(inputFlag == 4) {}
	//				else if(inputFlag == 5) {}
	//				else if(inputFlag == 6) {}
	//				else if(inputFlag == 7) {}
	//				else if(inputFlag == 8) {}
					
				
					//exit system
					else if(signInFlag == 4) {
						break;
					
					
					}
				}
			} 
			
			
		}
		//close scanner
		s.close();

	}


}
