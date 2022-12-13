import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Reports {
    private ArrayList<String> courseDetails;
    private String[] courseIDs;
    private Scanner scanner;
    private FileWriter fileWriter;
    private File file;
    private boolean starting;


    Reports(boolean starting) {
        Date today = new Date();
        courseDetails = new ArrayList<>();
        this.starting = starting;
        //Step 1: Find all files in the Courses folder
        courseIDs = new File(Global.CourseFolder).list();
        //Step 2: loop on every course, creating a Course object
        for (String id : courseIDs) {
            Course course = new Course(id.replaceFirst("[.][^.]+$", ""));
            //Step 3: compare depending on the starting variable (if starting => check start date, else check end date)
            if (starting){
                long diff = course.getStartDate().getTime() - today.getTime();
                long correctDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if (correctDiff >= 0 && correctDiff < 7) {
                    courseDetails.add(course.toString());
                }
            }
            else {
                long diff = course.getEndDate().getTime() - today.getTime();
                long correctDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if (correctDiff >= 0 && correctDiff < 7) {
                    courseDetails.add(course.toString());
                }
            }
        }
        //Write to file
        if (starting) {
            file = new File(Global.ReportFolder + "starting.txt");
        } else {
            file = new File(Global.ReportFolder + "ending.txt");
        }
        try {
            fileWriter = new FileWriter(file);
            for (String course: courseDetails) {
                fileWriter.write(course);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void printReport() {
        for (String course: courseDetails) {
            System.out.print(course);
            System.out.println("-----------------------");
        }
    }
}