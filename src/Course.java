import java.io.*;
import java.text.*;
import java.util.*;

public class Course implements EventListener {
    private String ID, name, room, price, parentCourseCode, instructorUsername;
    private Date startDate, endDate;
    private int days, grade;
    private ArrayList<String> studentUsernames;
    private FileHandler fileHandler;
    private FileHandler surveyHandler;

    Course(String ID) {
        //This constructor READS from the file
        fileHandler = new FileHandler(Global.CourseFolder + ID + ".txt");
        fileHandler.create();
        surveyHandler = new FileHandler(Global.SurveyFolder + ID + ".txt");
        surveyHandler.create();
        studentUsernames = new ArrayList<>();
        this.ID = ID;
        this.read();
    }

    Course(String ID, String name, String parentCourseCode, String instructorUsername, String room, String price, int days, int grade, Date startDate, Date endDate, ArrayList<String> studentUsernames) {
        //This constructor CREATES the file
        this.ID = ID;
        this.name = name;
        this.parentCourseCode = parentCourseCode;
        this.instructorUsername = instructorUsername;
        this.room = room;
        this.price = price;
        this.days = days;
        this.grade = grade;
        this.startDate = (Date) startDate.clone();
        this.endDate = (Date) endDate.clone();
        this.studentUsernames = (ArrayList<String>) studentUsernames.clone();
        fileHandler = new FileHandler(Global.CourseFolder + ID + ".txt");
        fileHandler.create();
        update();
        //Creates a survey file
        surveyHandler = new FileHandler(Global.SurveyFolder + ID + ".txt");
        surveyHandler.create();
    } 

    public ArrayList<String> getStudentUsernames() {
        return studentUsernames;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getParentCourseCode() {
        return parentCourseCode;
    }

    public String getInstructorUsername() {
        return instructorUsername;
    }

    public int getGrade() {
        return grade;
    }

    public String toString() {
        String courseHeader = parentCourseCode + " - " + ID + " : " + name + "\n";
        String courseInstructor = "Instructor: " + instructorUsername + "\n";
        String coursePrice = "Price: " + price + "\n";
        String courseRoom = "Room: " + room + "\n";
        String courseStart = "Start Date: " + new SimpleDateFormat("dd-MM-yyyy").format(startDate) + "\n";
        String courseEnd = "End Date: " + new SimpleDateFormat("dd-MM-yyyy").format(endDate) + "\n";
        String courseStudents = "Students: \n";
        for (String student : studentUsernames) {
            courseStudents = courseStudents + student + "\n";
        }
        String courseInfo = courseHeader + courseInstructor + coursePrice + courseRoom
                            + courseStart + courseEnd + courseStudents;
        return courseInfo;
    }

    public boolean removeStudent(String student) {
        //This method removes a student from the array list then updates the file
        if (studentUsernames.contains(student)) {
            studentUsernames.remove(student);
            update();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String s = name + "\n" + parentCourseCode + "\n" + instructorUsername + "\n" + room + "\n" +
                price + "\n" + dateFormat.format(startDate) + "\n" + dateFormat.format(endDate) + "\n" + days + "\n" + grade;
        for (String usernames : studentUsernames) {
            s = s + "\n" + usernames;
        }
        fileHandler.update(s);
    }

    @Override
    public void read() {
        ArrayList<String> info = fileHandler.retrieve();
        this.name = info.get(0);
        this.parentCourseCode = info.get(1);
        this.instructorUsername = info.get(2);
        this.room = info.get(3);
        this.price = info.get(4);
        try {
            this.startDate = new SimpleDateFormat("dd-MM-yyyy").parse(info.get(5));
            this.endDate = new SimpleDateFormat("dd-MM-yyyy").parse(info.get(6));
        } catch (Exception e){}
        this.days = Integer.parseInt(info.get(7));
        this.grade = Integer.parseInt(info.get(8));
        if (info.size() > 9) {
            for (int i = 9; i < info.size(); i++) {
                studentUsernames.add(info.get(i));
            }
        }
    }

    @Override
    public void delete() {
        fileHandler.delete();
        surveyHandler.delete();
    }
}
