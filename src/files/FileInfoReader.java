package files;
import java.util.ArrayList;
//import java.util.Arrays;

import courses.*;
//import files.*;
import roles.*;
import java.io.*;
import java.io.BufferedReader;

public class FileInfoReader {
	
	
	//Instance variables
	/**
	 * File object for file to read
	 */
	File file = null;
	
	/**
	 * File reader object for creating buffered reader
	 */
	FileReader fileReader = null;
	
	/**
	 * BufferedReader object for reading file
	 */
	BufferedReader bufferedReader = null;
	
	//Constructor 
	/**
	 * Blank constructor as this is a static only class
	 */
	public FileInfoReader() {
	}
	
	//Reader methods
	/**
	 * Creates file, fileReader, and bufferedReader objects to read given fileName
	 * Creates User objects based on given file and userType and appends to users ArrayList
	 * @param fileName file to read
	 * @param userType type of User object to create
	 * @param users Arraylist to append new user to & passing to User Object
	 * @param courses ArrayList of courses for creating & passing to User Object
	 */
	public void readUsers(String fileName, String userType, ArrayList<User> users, ArrayList<Course> courses, ArrayList<Student> students, ArrayList<Professor> professors) {
		
		//Initialize file object
		this.file = new File(fileName);
		
		//Try to create file reader & catch FileNotFoundException
		try {
			this.fileReader = new FileReader(this.file);
		} catch (FileNotFoundException e) {
			//Print message to notify file was not found
			System.out.println("Sorry given file: " + fileName + " was not found");
		}
		//Initialize buffered reader
		this.bufferedReader = new BufferedReader(this.fileReader);
		
		//Create string for next line to read
		String line;
		
		//Read lines in file using budderedReader, catching any IOException
		try {
			//While there are lines in the file to read
			while ((line = bufferedReader.readLine()) != null) {
				
				//Split each line into an array of Strings based on "; " delimiter.
				String []lineArray = line.split("; ");
				
				//If given user type is admin, create admin object and append to users ArrayList
				if (userType == "admin") {
					
					//Create new admin object based on the position of the constructor parameters in lineArray
					Admin newUser = new Admin(lineArray[2], lineArray[3], lineArray[0], lineArray[1], courses, students, professors);
					
					//Append admin object to userList
					users.add(newUser);
					
				}//If given user type is student, create Student object and append to users ArrayList
				else if (userType == "student") {
					
					//Create new Student object based on the position of the constructor parameters in lineArray
					Student newUser = new Student(lineArray[2], lineArray[3], lineArray[0], lineArray[1], courses, students, professors);
					
					//NEED TO GET THE REST OF INFO FOR THE STUDENT// lineArray[4] gets broken down here
					newUser.readCourseAndGrade(lineArray[4]);
					
					//Append Student object to userList
					users.add(newUser);
					
				} //If given user type is professor, create professor object and append to users ArrayList
				else if (userType == "professor") {
					
					//Create new professor object based on the position of the constructor parameters in lineArray
					Professor newUser = new Professor(lineArray[2], lineArray[3], lineArray[0], lineArray[1], courses, students, professors);
					
					//Append Professor object to userList
					users.add(newUser);
					
				} //Otherwise notify that an invalid user type was not provided
				else {
					System.out.println("Invalid user type provided!");
					break;
				}
			}
		} //If IOException caught, print error message 
		catch (IOException e) {
			e.printStackTrace();
			
		} //Finally, close fileReader and buffered reader, again catching any IOexception 
		finally {
			try {
				this.fileReader.close();
				this.bufferedReader.close();
			} catch (IOException e) {
				//Print caught IO exception
				e.printStackTrace();
			}
		}
	}	
	
	
	
	
	
	//lfennell notes - I mostly copied your reader here, only thing is I changed it to take in the arraylist as a parameter & return nothing
	//It should edit the provided ArrayList by refrence, but we could've done it either way
	/**
	 * Creates file, fileReader, and bufferedReader objects to read given fileName
	 * Creates Course objects based on given file and appends to courses ArrayList
	 * @param fileName
	 * @param courseList
	 */
	public void readCourse(String fileName, ArrayList<Course> courseList) {

		try {
			String currLine;
			//open file
			//create file object
			File courseFile = new File(fileName);
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
	}


}
