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


    Student()
    {

    }


    Student(String userName ,String password ,String name , String phone,String age , ArrayList<String> courses)
    {
        try {
            writer = new FileWriter(Global.StudentLogin);
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
            for(int i =0 ;i<courses.size();i++)
            {
                writer.write(courses.get(i) +"\n");
                writer.write("\n");

            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Student(String userName)  {
        this.username = userName;
        read();
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        update();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        update();
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void addCourses(String course) {
        if(!courses.contains(course))
            courses.add(course);
        update();
    }

    public ArrayList<String> getGrades() {
        return grades;
    }


    @Override
    public void update()  {
        try {
            writer = new FileWriter(Global.StudentFolder+this.username+".txt");
            //writer.write(this.username+"\n");
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

    @Override
    public void read() {

        file = new File(Global.StudentFolder+this.username +".txt");
        try {
            Scanner read = new Scanner(file);

            //this.username = read.nextLine();
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
            lines.remove(lines.indexOf(username));
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

    public void addGrades(String courseID,String grade)
    {
        if(courses.contains(courseID)) {
            grades.set(courses.indexOf(courseID), grade);
            update();
        }

    }
    public void removeGrades(String courseID,String grade)
    {
        if(courses.contains(courseID)) {
            grades.set(courses.indexOf(courseID), "");
            update();
        }

    }

    public void removeCourse(String courseID)
    {
        if(courses.contains(courseID))
        {
            grades.remove(courses.indexOf(courseID));
            courses.remove(courseID);
            update();
        }

    }

    public boolean login(String Username, String Password) throws Exception {

        if(super.login(Username,Password,Global.StudentLogin))
        {
            read();
            return true;
        }
        else return false;
    }

    public String toString()
    {
        String header = this.username +" - "+name+"\n";
        String studentAge = "Age : "+age+"\n";
        String phoneNumber  = "phone : " +phone+"\n";
        String numberOfCourses ="courses number : "+ courses.size() + "\n";
        return header+studentAge+phoneNumber+numberOfCourses;
    }


}
