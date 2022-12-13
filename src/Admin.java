import java.util.*;
import java.io.*;

public class Admin extends Person {
    private ArrayList<String> instructorUsernames = new ArrayList<>();
    private ArrayList<String> studentUsernames = new ArrayList<>();

    public boolean login(String Username, String Password) throws Exception {
        return super.login(Username, Password, Global.AdminLogin);
    }

    public ArrayList<String> getInstructorUsernames() {
        //Updates the list
        readUsernames(instructorUsernames, Global.InstructorLogin);
        return instructorUsernames;
    }

    public ArrayList<String> getStudentUsernames() {
        //Updates the list
        readUsernames(studentUsernames, Global.StudentLogin);
        return studentUsernames;
    }

    private void readUsernames(ArrayList<String> list, String path) {
        //This method fills the list w/ files from a specific path, used for instructor and student usernames
        list.clear();
        File fileList = new File(path);
        try {
            Scanner listReader = new Scanner(fileList);
            while (listReader.hasNextLine()) {
                list.add(listReader.nextLine()); //Reading the usernames
                listReader.nextLine(); //Skipping the password line
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //This method lists the instructors in the system
    public void listInstructors() {
        //Finding all instructors through the login file
        readUsernames(instructorUsernames, Global.InstructorLogin);
        System.out.println("Number of instructors: " + instructorUsernames.size());
        System.out.println("----------");
        for (String str : instructorUsernames) {
            //Reading every instructor's details by creating an object w/ the username
            Instructor instructor = new Instructor(str);
            //Printing every instructor's details w/ their toString method
            System.out.print(instructor.toString());
            System.out.println("----------");
        }
    }

    //This method lists the students in the system
    public void listStudents() {
        //Finding all students through the login file
        readUsernames(studentUsernames, Global.StudentLogin);
        System.out.println("Number of students: " + studentUsernames.size());
        System.out.println("----------");
        for (String str : studentUsernames) {
            //Reading every student's details by creating an object w/ the username
            Student student = new Student(str);
            //Printing every student's details w/ their toString method
            System.out.print(student.toString());
            System.out.println("----------");
        }
    }

    //This method creates a new parent course and saves it
    public void createParentCourse(String parentCourseName, String parentCourseCode) {
        ArrayList<String> initialEmpty = new ArrayList<>();
        ParentCourse newParentCourse = new ParentCourse(parentCourseCode, parentCourseName, initialEmpty);
    }

    //This method updates the parent course's name
    public void updateParentCourse(String parentCourseCode, String newName) {
        ParentCourse parentCourse = new ParentCourse(parentCourseCode);
        parentCourse.setName(newName);
    }

    //This method deletes a parent course, its sub-courses and removes courses from instructors and students
    public void deleteParentCourse(String parentCourseCode) {
        ParentCourse parentCourse = new ParentCourse(parentCourseCode);
        //Getting individual courses from the parent course
        for (String courseID : parentCourse.getCourses()) {
            Course course = new Course(courseID);

            //Remove the course from the instructor
            Instructor instructor = new Instructor(course.getInstructorUsername());
            instructor.removeCourse(courseID);

            for (String studentUsername : course.getStudentUsernames()) {
                //Remove the course from the student
                Student student = new Student(studentUsername);
                student.removeCourse(courseID);
            }

            course.delete();
        }
        //Deleting the parent course file
        parentCourse.delete();
    }

    //This method creates an instructor
    public void addInstructor(String username, String password, String name, String phoneNumber, String salary) {
        ArrayList<String> initialEmpty = new ArrayList<>();
        Instructor newInstructor = new Instructor(username, password, name, phoneNumber, salary, initialEmpty);
    }

    //This method updates an instructor's details (name, phonenumber and salary)
    public void updateInstructor(String instructorUsername, String newName, String newPhoneNumber, String newSalary) {
        Instructor instructor = new Instructor(instructorUsername);
        if (!newName.isEmpty()) {
            instructor.setName(newName);
        }
        if (!newPhoneNumber.isEmpty()) {
            instructor.setPhoneNumber(newPhoneNumber);
        }
        if (!newSalary.isEmpty()) {
            instructor.setSalary(newSalary);
        }
    }

    //This method deletes an instructor
    public void deleteInstructor(String instructorUsername) {
        Instructor instructor = new Instructor(instructorUsername);
        for (String courseID : instructor.getCourseID()) {
            Course course = new Course(courseID);

            //Removes the course from the parent course
            ParentCourse parentCourse = new ParentCourse(course.getParentCourseCode());
            parentCourse.removeCourse(courseID);

            for (String studentUsername : course.getStudentUsernames()) {
                //Removes the course from the students
                Student student = new Student(studentUsername);
                student.removeCourse(courseID);
            }

            //Deletes the course file
            course.delete();
        }
        //Deletes the instructor file
        instructor.delete();
    }

    public void addStudent() {
        
    }
    
    public void updateStudent() {
        
    }
    
    public void deleteStudent() {

    }

    public void createCourse() {

    }

    public void createReport() {

    }
}
