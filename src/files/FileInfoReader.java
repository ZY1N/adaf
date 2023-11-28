package files;
import java.util.ArrayList;
//import java.util.Arrays;

import courses.*;
//import files.*;
import roles.*;
import java.io.*;
import java.io.BufferedReader;

public class FileInfoReader {
	
	
	//blank private constructor as this is a static only class
	private FileInfoReader() {
	}
	
	
	public static ArrayList<Admin> readAdmin() {
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		

		try {
			String currLine;
			//open file
			//create file object
			File adminFile = new File("./adminInfo.txt");
			//check filenotfoundexception
			FileReader fileReader = new FileReader(adminFile);
			//temp admin object
			Admin tempAdmin;
					
			//open buffered reader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
						
			//read the data into adminList
			while((currLine = bufferedReader.readLine()) != null) {
				
				//splits string based on collon
				String []strArray = currLine.split("; ");
				
				//check to see if currLine is valid
				//must be 4 otherwise ignore that line
				if(strArray.length != 4) {
					continue ;
				}
				
				//turn currline into admin object
				tempAdmin = new Admin(strArray[0], strArray[1], strArray[2], strArray[3]);
				
				adminList.add(tempAdmin);
			}
			fileReader.close();
			bufferedReader.close();
		}
		//file fail to open
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		//stream fail, line read fail
		catch(IOException e) {
			System.out.println(e);
		}

		return(adminList);
	}
	
	public static ArrayList<Professor> readProfessor() {
		
		ArrayList<Professor> professorList = new ArrayList<Professor>();

		try {
			String currLine;
			//open file
			//create file object
			File professorFile = new File("./profInfo.txt");
			//check filenotfoundexception
			FileReader fileReader = new FileReader(professorFile);
			//temp admin object
			Professor tempProfessor;
					
			//open buffered reader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
						
			//read the data into adminList
			while((currLine = bufferedReader.readLine()) != null) {
				
				//splits string based on collon
				String []strArray = currLine.split("; ");
				
				//check to see if currLine is valid
				//must be 4 otherwise ignore that line
				if(strArray.length != 4) {
					continue ;
				}
				
				//turn currline into admin object
				tempProfessor = new Professor(strArray[0], strArray[1], strArray[2], strArray[3]);
				
				professorList.add(tempProfessor);
			}
			fileReader.close();
			bufferedReader.close();
		}
		//file fail to open
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		//stream fail, line read fail
		catch(IOException e) {
			System.out.println(e);
		}

		return(professorList);
		
	}

	
	
	public static ArrayList<Student> readStudent(ArrayList<Course> courseList) {
		ArrayList<Student> studentList = new ArrayList<Student>();

		try {
			String currLine;
			//open file
			//create file object
			File studentFile = new File("./studentInfo.txt");
			//check filenotfoundexception
			FileReader fileReader = new FileReader(studentFile);
			//temp admin object
			Student tempStudent;
					
			//open buffered reader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
						
			//read the data into adminList
			while((currLine = bufferedReader.readLine()) != null) {
				
				//splits string based on collon
				String []strArray = currLine.split("; ");
				
				//check to see if currLine is valid
				//must be 4 otherwise ignore that line
				if(strArray.length != 5) {
					continue ;
				}
				
				//turn currline into admin object
				tempStudent = new Student(strArray[0], strArray[1], strArray[2], strArray[3]);
				
				//NEED TO GET THE REST OF INFO FOR THE STUDENT//
				tempStudent.readCourseAndGrade(strArray[4], courseList);
				
				studentList.add(tempStudent);
			}
			fileReader.close();
			bufferedReader.close();
		}
		//file fail to open
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		//stream fail, line read fail
		catch(IOException e) {
			System.out.println(e);
		}
		return(studentList);
	}

	
	public static ArrayList<Course> readCourse() {
		ArrayList<Course> courseList = new ArrayList<Course>();

		try {
			String currLine;
			//open file
			//create file object
			File courseFile = new File("./courseInfo.txt");
			//check filenotfoundexception
			FileReader fileReader = new FileReader(courseFile);
			//temp admin object
			Course tempCourse;
					
			//open buffered reader
			BufferedReader bufferedReader = new BufferedReader(fileReader);
						
			//read the data into adminList
			while((currLine = bufferedReader.readLine()) != null) {
				
				//splits string based on collon
				String []strArray = currLine.split("; ");
				
				//check to see if currLine is valid
				//must be 4 otherwise ignore that line
				if(strArray.length != 7) {
					continue ;
				}
				
				//turn currline into admin object
				tempCourse = new Course(strArray[0], strArray[1], strArray[2], strArray[3], strArray[4], strArray[5], strArray[6]);
				
				courseList.add(tempCourse);
			}
			fileReader.close();
			bufferedReader.close();
		}
		//file fail to open
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		//stream fail, line read fail
		catch(IOException e) {
			System.out.println(e);
		}
		return(courseList);
	}


}
