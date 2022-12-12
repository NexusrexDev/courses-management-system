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
        this.startDate = (Date)startDate.clone();
        this.endDate = (Date)endDate.clone();
        this.studentUsernames = (ArrayList<String>)studentUsernames.clone();
        update();
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

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String s = name + "\n" + parentCourseCode + "\n" + instructorUsername + "\n" + room + "\n" +
                price + "\n" + dateFormat.format(startDate) + "\n" + dateFormat.format(endDate) + "\n" + days + "\n" + grade;
        for (String usernames : studentUsernames) {
            s = s + "\n" + usernames;
        }
        return s;
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
        File file = new File( ID + ".txt");
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(this.toString());
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void read() {
        File file = new File( ID + ".txt");
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
        File file = new File(ID + ".txt");
        file.delete();
    }
}
