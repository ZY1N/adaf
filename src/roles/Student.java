package roles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import courses.Course;


public class Student extends User {

	//keep course and grade in hash map
    public HashMap<String, Course> Schedule = new HashMap<String, Course>();
    private ArrayList <String> coursesAndGrades = new ArrayList <String>();
    
    //not enough. there are many courses, keeping them in one line is useless. instead i put them in the coursesandgrades arraylist
    // in the correct format
	//private String coursesInfo;



 
	public Student(String id, String name, String username, String password, ArrayList <Course> courses, ArrayList<Student> students, ArrayList<Professor> professors) {
		super(username, password, name, id, courses, students, professors);

		this.userType = "Student";
	}
	
	/**
	 * Prints menu of action items for given subclass of User
	 * Must implement to override abstract method
	 */
	@Override
	public void printActionMenu() {
		// TODO Auto-generated method stub
		
		//Print Action menu message
		System.out.println("--------------------------------");
		System.out.println("Welcome, " + this.getName());
		System.out.println("--------------------------------");
		System.out.println("1 -- View all courses");
		System.out.println("2 -- Add courses to your list");
		System.out.println("3 -- View selected courses");
		System.out.println("4 -- Drop courses in your list");
		System.out.println("5 -- View grades");
		System.out.println("6 -- Return to previous menu");	
		System.out.println("");
		System.out.println("Please enter your option, eg. '1'.");
		System.out.println("");

	}

	/**
	 * Prompts for user input and performs action based on given list of actions for subclass of User
	 */
	@Override
	public void performAction(Scanner s) {
		
		//Create array of valid user actions
		String[] loginOptions = {"1", "2", "3", "4", "5", "6", "7", "8"};
		
		//Create scanner for user input
		Scanner scan = new Scanner(System.in);
		
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
			this.addCourse(s);
			
		} else if(userInput.equals("3")) {
			this.viewSelectedCourses();
			
		} else if(userInput.equals("4")) {
			this.dropCourse();
			
		} else if(userInput.equals("5")) {
			this.viewGrades();
			
		} else if(userInput.equals("6")) {	
			scan.close();
		} 
		//Close scanner
		scan.close();
	}
	
	
	
	/**
	 * Displays a list of all courses available
	 */
	public void viewAllCourses() {
		for(Course each : courses) {
			System.out.println(each);
		}
	}
	
	/**
	 * Add a given course to student schedule.
	 * Courses can only be added if not already done so previously and there is no time conflict with another course
	 */
	public void addCourse(Scanner s) {
		
		//Loop though until press 'q' to exit
		while(true) {
			//fix this when scanner is fixed
			String inputString = s.next();
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
			for(Course each : courses) {
				
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
			if(! this.Schedule.containsKey(inputString)){
				//check for time conflicts
				timeConflict = checkTimeConflict(tempCourse.getDays(), tempCourse.getTimeStart(), tempCourse.getTimeEnd(), this.Schedule);
				maxCapacityReached = checkMaxCapacity(tempCourse.getCourseCapacity(), tempCourse.getStudentNum());
				
				//if there is no conflict then okay it
				if(!timeConflict && !maxCapacityReached) {

					//if it exists add to student hashmap
					this.Schedule.put(inputString, tempCourse);	
					
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
	
	/**
	 * Displays all courses in which the student is currently enrolled
	 */
	public void viewSelectedCourses() {
		
		System.out.println("The courses in your list");
		
		for (String key : this.Schedule.keySet()) {
			System.out.println(key);
		}
	}
	
	/**
	 * Removes given course from student list & removes student from course
	 */
	public void dropCourse() {
		System.out.println("Here are the courses you already taken, with your grade in a letter format");
		for(String each : coursesAndGrades) {
			System.out.println(each);
		}
	
	}
	
	/**
	 * Shows student grade for each course enrolled
	 */
	public void viewGrades() {
		
	}
	
	/*
	 * do these later
	 * */
	
	private boolean checkMaxCapacity(int a, int b) {
		
		if(a <= b) {
			System.out.println(a + " : " + b);

			System.out.println("Can't add course. Max capacity reached.");
			return true;
		}
		return false;
	}
	
	private int convertTimeInt(String time) {
		int timeInt;
		
		//break into two
		timeInt = Integer.parseInt(time.split(":")[0])*100 + Integer.parseInt(time.split(":")[1]);
		
		return(timeInt);
		
	}
	
	private  boolean checkTimeConflict(String days, String timeStart, String timeEnd, HashMap<String, Course> Schedule) {
		boolean timeConflict = false;
		
		//turn string into time
		int timeStartInt = convertTimeInt(timeStart);
		int timeEndInt = convertTimeInt(timeEnd);
		
		int timeStartIntComp;
		int timeEndIntComp;
		
		//compare day letter by letter		
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
	
	/**
	 * Calls constructor for superclass user and takes additional parameter "type"
	 * @param userName for User
	 * @param password for User
	 * @param name for User
	 * @param id for User
	 * @param courses ArrayList of all courses available
	 */
	
	/**
	 *Overides toString to the format specified 
	 */
	@Override
    public String toString() {
        return ("Id:" + this.getId() + "\nName:" + this.getName() +  "\nUsername:" + this.getUserName() + "\nPassword:" + this.getPassword());
    }
	
	
	
	
	

	public void readCourseAndGrade(String rawLine) {
		
		String []gradesArray = rawLine.split(", ");
		String courseId;
		String courseName;
		String grade;
		
		for(String each : gradesArray) {
			
			//protection, ignore if there aren't 2 parts to it
			if(each.split(": ").length != 2) {
				continue;
			}
			
			//split the data
			courseId = each.split(": ")[0];
			grade = each.split(": ")[1];
			
			//get the course Name
			for(Course currCourse : courses) {
				if(currCourse.getCourseCode().equals(courseId)) {
					courseName = currCourse.getCourseName();
					coursesAndGrades.add("Grade of " + courseId + " " + courseName + ":" + grade);
					break;
				}
			}
		}
	}
		

		
		
//		//check to see if course already exists
//		if(coursesAndGrades.containsKey(course))
//		{
//			System.out.println("Already exists");
//		}
//		else {
//			coursesAndGrades.put(course, grade);
//		}
	
	/*
	public String getName() {
		return this.username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

    public String toString() {
        return ("Id:" + id + "\nName:" + name +  "\nUsername:" + username + "\nPassword:" + password);
    }

	public String getId() {
		return this.id;
	}

	public void printGrades() {
		System.out.println("Here are the courses you already taken, with your grade in a letter format");
		for(String each : coursesAndGrades) {
			System.out.println(each);
		}
	}

	public void printSelectedCourses() {
		
		System.out.println("The courses in your list");
		
		for (String key : this.Schedule.keySet()) {
			System.out.println(key);
		}
	} */

}
