import java.util.*;
import java.io.*;

public class Instructor extends Person implements EventListener{

    private String name, phoneNumber, salary;
    private FileWriter fileWriter;
    private ArrayList<String> courseID = new ArrayList<>();


    // 3 constructors
    // one to create, another to show, another empty for logging in.
    public Instructor(){

    }

    public Instructor(String username) {
        this.username=username;
        this.read();
    }

    public Instructor(String username, String password, String name, String phoneNumber, String salary, ArrayList<String> courseID){
        this.username=username;
        this.password=password;
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.salary=salary;
        this.courseID= (ArrayList<String>) courseID.clone();
        //Appending the username/password to the login file
        File instructorsFile = new File(Global.InstructorLogin);
        try {
            fileWriter = new FileWriter(instructorsFile);
            fileWriter.append(username + "\n");
            fileWriter.append(password + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.update();
    }
    // override login -> call super for the path, if true load.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        update();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
        update();
    }

    public ArrayList<String> getCourseID() {
        return courseID;
    }

    public boolean login(String username, String password) throws Exception {
        boolean loggedIn= super.login(username, password, Global.InstructorLogin );
        if (loggedIn){
            this.read();
            return true;
        }
        else
            return false;
    }

    // setGrade(String courseID, String userID, String grade) function
    public boolean setGrade(String courseID, String userID, String grade){
        Course c = new Course(courseID);
        if (c.getStudentUsernames().contains(userID)) {
            Student student = new Student();
            student.addGrades(courseID,grade);
            return true;
        }
        else
            return false;
        }

    // remove course
    public boolean removeCourse(String course){
        if (this.courseID.contains(course)){
            courseID.remove(course);
            update();
            return true;
        }
        else return false;
    }

    // override toString
    public String toString(){
        String instructorHeader = username + " - " + name + "\n";
        String instructorPhoneNum = "Phone number: " + phoneNumber + "\n";
        String instructorSalary = "Salary: " + salary + "\n";
        String instructorCourses = "Number of taught courses: " + courseID.size() + "\n";
        String instructorInfo = instructorHeader + instructorPhoneNum + instructorSalary + instructorCourses;
        return instructorInfo;
    }

    // update function
    @Override
    public void update() {
        File instructorFile = new File(Global.InstructorFolder + username + ".txt");
        try {
            fileWriter = new FileWriter(instructorFile);
            String i = name + "\n" + phoneNumber + "\n" + salary;
            for (String courses: courseID){
                i = i + "\n" + courses;
            }
            fileWriter.write(i);
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // delete instructor
    @Override
    public void delete(){
        //remove username and password for said instructor
        ArrayList<String> info = new ArrayList<String>();
        File loginFile = new File("Instructors.txt");
        try {
            Scanner scanner = new Scanner(loginFile);
            while (scanner.hasNextLine()){
                info.add(scanner.nextLine());
            }
            scanner.close();
            if (info.contains(username)){
                info.remove(info.indexOf(username)+1);
                info.remove(info.indexOf(username));
                fileWriter = new FileWriter(loginFile);
                for (String i: info) {
                    fileWriter.write(i+"\n");
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        // remove the instructor file itself
        File instructorFile = new File(username + ".txt");
        instructorFile.delete();

    }

    @Override
    public void read(){
        File instructorFile = new File(Global.InstructorFolder + username + ".txt");
        try {
            Scanner scanner = new Scanner(instructorFile);
            name = scanner.nextLine();
            phoneNumber = scanner.nextLine();
            salary = scanner.nextLine();
            while (scanner.hasNextLine())
            {
                courseID.add(scanner.nextLine());
            }
            if (courseID.contains("")){
                courseID.remove("");
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

