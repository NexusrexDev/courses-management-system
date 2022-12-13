import java.util.*;
import java.io.*;

public class Student extends Person implements EventListener {

    private String phone ;
    private String name ;
    private String age;

    private ArrayList<String> courses = new ArrayList<>();
    private ArrayList<String> grades = new ArrayList<>();
    private FileWriter writer;
    private File file;

    // default constructor
    Student()
    {

    }

    // constructor to make student
    Student(String userName ,String password ,String name , String phone,String age , ArrayList<String> courses)
    {
        try {
            writer = new FileWriter(Global.StudentLogin, true);
            writer.append(userName).append("\n");
            writer.append(password).append("\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            writer = new FileWriter(Global.StudentFolder+userName+".txt");
            //writer.write(userName +"\n");
            writer.write(name +"\n");
            writer.write(phone +"\n");
            writer.write(age +"\n");
            for(String course :courses)
            {
                writer.write(course +"\n");
                writer.write("\n");

            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // constructor to read student data
    Student(String userName)  {
        this.username = userName;
        read();
    }
    // return phone number
    public String getPhone() {
        return phone;
    }
    // update phone number
    public void setPhone(String phone) {
        this.phone = phone;
        update();
    }
    // get student name
    public String getName() {
        return name;
    }

    //update student name
    public void setName(String name) {
        this.name = name;
        update();
    }
    //rerun student age
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        update();
    }
    // return student courses
    public ArrayList<String> getCourses() {
        return courses;
    }

    // add course
    public void addCourses(String course) {
        if(!courses.contains(course))
        {
            courses.add(course);
            grades.add("");
        }
        update();
    }
    // return student grad
    public ArrayList<String> getGrades() {
        return grades;
    }


    // update changes of user data
    @Override
    public void update()  {
        try {
            writer = new FileWriter(Global.StudentFolder+this.username+".txt");
            writer.write(name +"\n");
            writer.write(phone +"\n");
            writer.write(age +"\n");
            for(int i =0 ;i<courses.size();i++)
            {
                writer.write(courses.get(i) +"\n");
                writer.write(grades.get(i)+"\n");

            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //fill student attribute with file data
    @Override
    public void read() {

        file = new File(Global.StudentFolder+this.username +".txt");
        try {
            Scanner read = new Scanner(file);


            name = read.nextLine();
            phone = read.nextLine();
            age = read.nextLine();
            while (read.hasNextLine())
            {

                courses.add(read.nextLine());
                grades.add(read.nextLine());
            }


            read.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // delete student
    @Override
    public void delete() {

        file = new File(Global.StudentLogin);
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine())
                lines.add(read.nextLine());
            read.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        if(lines.contains(username))
        {
            lines.remove(lines.indexOf(username)+1);
            lines.remove(username);
        }


        try {
            writer =new FileWriter(Global.StudentLogin);

            for(String line : lines)
                writer.write(line+"\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        file = new File(Global.StudentFolder+this.username+".txt");
        file.delete();


    }
    // add grade
    public void addGrades(String courseID,String grade)
    {
        if(courses.contains(courseID)) {
            grades.set(courses.indexOf(courseID), grade);
            update();
        }

    }
    // remove grade
    public void removeGrades(String courseID,String grade)
    {
        if(courses.contains(courseID)) {
            grades.set(courses.indexOf(courseID), "");
            update();
        }

    }
    //remove course
    public void removeCourse(String courseID)
    {
        if(courses.contains(courseID))
        {
            grades.remove(courses.indexOf(courseID));
            courses.remove(courseID);
            update();
        }

    }
    // add survey
    public void createSurvey(String courseID, String comment)
    {
        if (courses.contains(courseID)) {
            file = new File(Global.SurveyFolder + courseID + ".txt");
            try {
                writer = new FileWriter(file, true);
                writer.append(username).append(" : ").append(comment).append("\n");
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // login student and exception
    public boolean login(String Username, String Password) throws NonexistentUserException {

        if(super.login(Username,Password,Global.StudentLogin))
        {
            read();
            return true;
        }
        else throw new NonexistentUserException();
    }

    // return student data
    public String toString()
    {
        String header = this.username +" - "+name+"\n";
        String studentAge = "Age : "+age+"\n";
        String phoneNumber  = "Phone number : " +phone+"\n";
        String numberOfCourses ="Number of registered courses : "+ courses.size() + "\n";
        return header+studentAge+phoneNumber+numberOfCourses;
    }


}
