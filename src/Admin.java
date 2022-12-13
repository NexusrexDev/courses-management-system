import java.util.*;
import java.io.*;

public class Admin extends Person {
    public boolean login(String Username, String Password) throws Exception {
        return super.login(Username, Password, Global.AdminLogin);
    }

    //This method lists the instructors in the system
    public void listInstructors() {
        //Finding all instructors through the login file
        File fileList = new File(Global.InstructorLogin);
        ArrayList<String> instructorUsernames = new ArrayList<>();
        try {
            Scanner listReader = new Scanner(fileList);
            while (listReader.hasNextLine()) {
                instructorUsernames.add(listReader.nextLine()); //Reading the usernames
                listReader.nextLine(); //Skipping the password line
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Number of students: " + instructorUsernames.size());
        System.out.println("----------");
        for (String str : instructorUsernames) {
            //Reading every instructor's details by creating an object w/ the username
            Instructor instructor = new Instructor(str);
            //Printing every instructor's details w/ their toString method
            System.out.println(instructor.toString());
            System.out.println("----------");
        }
    }

    //This method lists the students in the system
    public void listStudents() {
        //Finding all students through the login file
        File fileList = new File(Global.StudentLogin);
        ArrayList<String> studentUsernames = new ArrayList<>();
        try {
            Scanner listReader = new Scanner(fileList);
            while (listReader.hasNextLine()) {
                studentUsernames.add(listReader.nextLine()); //Reading the usernames
                listReader.nextLine(); //Skipping the password line
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Number of students: " + studentUsernames.size());
        System.out.println("----------");
        for (String str : studentUsernames) {
            //Reading every student's details by creating an object w/ the username
            Student student = new Student(str);
            //Printing every student's details w/ their toString method
            System.out.println(student.toString());
            System.out.println("----------");
        }
    }
}
