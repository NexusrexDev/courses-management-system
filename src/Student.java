import java.util.*;
import java.io.*;

public class Student extends Person implements EventListener {

    private String phone ;
    private String name ;
    private String age;

    private ArrayList<String> courses = new ArrayList<>();
    private ArrayList<String> grades = new ArrayList<>();


    private FileHandler writer;

    // default constructor
    Student()
    {

    }

    // constructor to make student
    Student(String userName ,String password ,String name , String phone,String age , ArrayList<String> courses)
    {
        writer = new FileHandler(Global.StudentLogin);
        writer.append(userName,password);
        writer = new FileHandler(Global.StudentFolder+userName+".txt");
        writer.update(name +"\n");
        writer.update(phone +"\n");
        writer.update(age +"\n");
        for(String course :courses)
        {
            writer.update(course +"\n");
            writer. update("\n");

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
        writer = new FileHandler(Global.StudentFolder+this.username+".txt");
        writer.update(name +"\n");
        writer.update(phone +"\n");
        writer.update(age +"\n");
        for(int i =0 ;i<courses.size();i++)
        {
            writer.update(courses.get(i) +"\n");
            writer.update(grades.get(i)+"\n");

        }

    }

    //fill student attribute with file data
    @Override
    public void read() {

        writer = new FileHandler(Global.StudentFolder+this.username +".txt");
        ArrayList<String> content = new ArrayList<>();
        content = writer.retrieve();
        name = content.get(0);
        phone = content.get(1);
        age = content.get(2);
        for(int i =3 ; i<content.size()-1;i+=2)
        {

            courses.add(content.get(i));
            grades.add(content.get(i+1));
        }


    }

    // delete student
    @Override
    public void delete() {

        writer = new FileHandler(Global.StudentLogin);
        ArrayList<String> lines = new ArrayList<>();
        lines = writer.retrieve();


        if(lines.contains(username))
        {
            lines.remove(lines.indexOf(username)+1);
            lines.remove(username);
        }

        for (String line : lines)
        {
            writer.update(line+"\n");
        }

        writer = new FileHandler(Global.StudentFolder+this.username+".txt");
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
            writer = new FileHandler(Global.SurveyFolder + courseID + ".txt");
            writer.append(username+" : "+comment);
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
