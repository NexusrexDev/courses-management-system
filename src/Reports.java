import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Reports {
    private ArrayList<String> courseDetails, courseIDs;
    private Scanner scanner;
    private FileHandler fileHandler;
    private boolean starting;


    Reports(boolean starting) {
        Date today = new Date();
        courseDetails = new ArrayList<>();
        //Step 1: Find all files in the Courses folder
        courseIDs = Global.getDirectoryList(Global.CourseFolder);
        //Step 2: loop on every course, creating a Course object
        for (String id : courseIDs) {
            Course course = new Course(id);
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
            fileHandler = new FileHandler(Global.ReportFolder + "starting.txt");
        }
        else {
            fileHandler = new FileHandler(Global.ReportFolder + "ending.txt");
        }

        String report = "Report create on: " + new SimpleDateFormat("dd-MM-yyyy").format(today) + "\n";

        for (String course: courseDetails)
        {
            report = report + course;
        }
        fileHandler.create();
        fileHandler.update(report);

        /*try {
            fileHandler = new FileWriter(file);
            fileWriter.write("Report created on: " +
                    new SimpleDateFormat("dd-MM-yyyy").format(today) + "\n");
            for (String course: courseDetails) {
                fileHandler.write(course);
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/


    }
}