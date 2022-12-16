import java.util.*;
import java.io.*;

public class Admin extends Person {

    public boolean login(String Username, String Password) throws NonexistentUserException {
         if(super.login(Username, Password, Global.AdminLogin))
             return true;
         else throw new NonexistentUserException();
    }

    //This method creates a new parent course and saves it
    public void createParentCourse(String parentCourseName, String parentCourseCode) {
        ArrayList<String> initialEmpty = new ArrayList<>();
        ParentCourse newParentCourse = new ParentCourse(parentCourseCode, parentCourseName, initialEmpty);
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

    //This method creates a student
    public void addStudent(String username, String password, String name, String phone, String age) {
        ArrayList<String> initialEmpty = new ArrayList<>();
        Student newStudent = new Student(username, password, name, phone, age, initialEmpty);
    }

    //This method deletes a student
    public void deleteStudent(String studentUsername) {
        Student student = new Student(studentUsername);

        for(String courseID :student.getCourses())
        {
            Course course = new Course(courseID);
            course.removeStudent(studentUsername);

        }
        student.delete();

    }

    //This method creates a course
    public void createCourse(String ID, String name, String parentCourseCode, String instructorUsername, String room, String price, int days, int grade, Date startDate, Date endDate, ArrayList<String> studentUsernames) {
        Course newCourse = new Course(ID, name, parentCourseCode, instructorUsername, room, price, days, grade, startDate, endDate, studentUsernames);
        ParentCourse parentCourse = new ParentCourse(parentCourseCode);
        parentCourse.addCourse(ID);
        Instructor instructor = new Instructor(instructorUsername);
        instructor.addCourse(ID);
        for (String studentUsername : studentUsernames) {
            Student student = new Student(studentUsername);
            student.addCourses(ID);
        }
    }

    //This method creates a report file
    public void createReport(boolean starting) {
        Reports report = new Reports(starting);
    }

    public void viewReport(boolean starting) {
        File file;
        if (starting) {
            file = new File(Global.ReportFolder + "starting.txt");
        } else {
            file = new File(Global.ReportFolder + "ending.txt");
        }
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                System.out.println(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
