package courses;

public class Course {
	
	//variables
	private String courseCode;
	private String courseName;
	private String courseInstructor;
	private String courseCapacity;
	private String days;
	private String timeEnd;
	private String timeStart;
	private int studentNum;

	
	//getters
	//setters

	public Course(String courseCode, String courseName, String courseInstructor, String days, String timeStart, String timeEnd, String capacity) {
		// TODO Auto-generated constructor stub
		
		//init the variables
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseInstructor = courseInstructor;
		this.courseCapacity = capacity;
		this.days = days;
		this.timeEnd = timeEnd;
		this.timeStart = timeStart;
		this.studentNum = 0;
	}

	
    public String toString() {
        return (courseCode + "|" + courseName + "," + timeStart + "-" + timeEnd + " on " + days + ", with course capacity: " + courseCapacity + " students: " + studentNum + ", lecturer: " + courseInstructor);
    }
    
    public String getCourseCode() {
    	return this.courseCode;
    }


	public String getCourseName() {

		return this.courseName;
	}


	public Object getCourseInstructor() {
		// TODO Auto-generated method stub
		return this.courseInstructor;
	}
	

	//admin
	//add course
	//delete course
	//students
	//add to capacity
	//check capacity
	//subtract capacity
	//check time conflict
	
	
}
