package roles;
import java.util.ArrayList;
import java.util.HashMap;

import courses.Course;


public class Student extends User {

	//keep course and grade in hash map
    public HashMap<String, Course> Schedule = new HashMap<String, Course>();
    private ArrayList <String> coursesAndGrades = new ArrayList <String>();

    
 
	public Student(String id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;	
		this.userType = "Student";
	}
	

	public void readCourseAndGrade(String rawLine, ArrayList <Course> courseList) {
		
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
			for(Course currCourse : courseList) {
				if(currCourse.getCourseCode().equals(courseId)) {
					courseName = currCourse.getCourseName();
					coursesAndGrades.add("Grade of " + courseId + " " + courseName + ":" + grade);
					break;
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
	}
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
	}

}
