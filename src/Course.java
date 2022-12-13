import java.io.*;
import java.text.*;
import java.util.*;

public class Course implements EventListener {
    private String ID, name, room, price, parentCourseCode, instructorUsername;
    private Date startDate, endDate;
    private int days, grade;
    private ArrayList<String> studentUsernames;
    private Scanner scanner;
    private FileWriter fileWriter;

    Course(String ID) {
        //This constructor READS from the file
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
        update();
        //Creates a survey file
        File surveyFile = new File(Global.SurveyFolder + ID + ".txt");
        try {
            surveyFile.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public String toString() {
        String courseHeader = parentCourseCode + " - " + ID + " : " + name + "\n";
        String courseInstructor = "Instructor: " + instructorUsername + "\n";
        String coursePrice = "Price: " + price + "\n";
        String courseRoom = "Room: " + room + "\n";
        String courseStart = "Start Date: " + new SimpleDateFormat("dd-MM-yyyy").format(startDate) + "\n";
        String courseEnd = "End Date: " + new SimpleDateFormat("dd-MM-yyyy").format(endDate) + "\n";
        String courseStudents = "Number of students: " + studentUsernames.size() + "\n";
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
        File file = new File( Global.CourseFolder + ID + ".txt");
        try {
            fileWriter = new FileWriter(file);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String s = name + "\n" + parentCourseCode + "\n" + instructorUsername + "\n" + room + "\n" +
                    price + "\n" + dateFormat.format(startDate) + "\n" + dateFormat.format(endDate) + "\n" + days + "\n" + grade;
            for (String usernames : studentUsernames) {
                s = s + "\n" + usernames;
            }
            fileWriter.write(s);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void read() {
        File file = new File( Global.CourseFolder + ID + ".txt");
        try {
            scanner = new Scanner(file);
            name = scanner.nextLine();
            parentCourseCode = scanner.nextLine();
            instructorUsername = scanner.nextLine();
            room = scanner.nextLine();
            price = scanner.nextLine();
            startDate = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
            endDate = new SimpleDateFormat("dd-MM-yyyy").parse(scanner.nextLine());
            days = scanner.nextInt();
            grade = scanner.nextInt();
            while (scanner.hasNextLine()) {
                studentUsernames.add(scanner.nextLine());
            }
            if (studentUsernames.contains("")) {
                //Cheap bugfix
                studentUsernames.remove("");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

    @Override
    public void delete() {
        File courseFile = new File(Global.CourseFolder + ID + ".txt");
        courseFile.delete();
        File surveyFile = new File(Global.SurveyFolder + ID + ".txt");
        surveyFile.delete();
    }
}
