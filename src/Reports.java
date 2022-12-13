import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;



public class Reports {

    private String ID, name, room, price, parentCourseCode, instructorUsername;
    private Date startDate, endDate;
    private int days, grade;
    private ArrayList<String> studentUsernames;
    private Scanner scanner;
    private FileWriter fileWriter;

    private boolean starting ;

    Course details = new Course(ID, name,parentCourseCode,instructorUsername,room,price,days,grade,startDate,endDate,studentUsernames);

    ArrayList<Object> COURSE_details;

//    ArrayList<Course> details2 ;



    public boolean Reports(Date startingDate){





        //Date todayDate = details.getStartDate();


        SimpleDateFormat format = new SimpleDateFormat ("dd-MM-yyyy");





        return false;



    }



}