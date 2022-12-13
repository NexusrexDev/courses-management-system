import java.util.*;
import java.io.*;

public class Admin extends Person {
    private ArrayList<String> instructorUsernames = new ArrayList<>();
    private ArrayList<String> studentUsernames = new ArrayList<>();

    public boolean login(String Username, String Password) throws Exception {
        return super.login(Username, Password, Global.AdminLogin);
    }

    public void readUsernames(ArrayList<String> list, String path) {
        //This method fills the list w/ files from a specific path, used for instructor and student usernames
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

    public void createParentCourse(String parentCourseName, String parentCourseCode) {
        //This method creates a new parent course and saves it
        ArrayList<String> initialEmpty = new ArrayList<>();
        ParentCourse newParentCourse = new ParentCourse(parentCourseName, parentCourseCode, initialEmpty);
    }

    public void deleteParentCourse(String parentCourseCode) {
        /*This method deletes a parent course, its children (courses) and removes courses from instructors
        and students*/
        ParentCourse parentCourse = new ParentCourse(parentCourseCode);
        parentCourse.delete();
    }
}
