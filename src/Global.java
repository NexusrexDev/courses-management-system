import java.io.*;
import java.util.*;

public final class Global {
    public static final String AdminLogin = "Login/Admins.txt";
    public static final String InstructorLogin = "Login/Instructors.txt";
    public static final String StudentLogin = "Login/Students.txt";
    public static final String StudentFolder = "Students/";
    public static final String InstructorFolder = "Instructors/";
    public static final String CourseFolder = "Courses/";
    public static final String ParentCourseFolder = "ParentCourses/";
    public static final String ReportFolder = "Reports/";
    public static final String SurveyFolder = "Surveys/";

    public static ArrayList<String> getUsernameList(String path) {
        //This method fills a list w/ data from a specific path, used for instructor and student usernames
        ArrayList<String> list = new ArrayList<>();
        FileHandler fileHandler = new FileHandler(path);
        fileHandler.create();
        ArrayList<String> fullSet = fileHandler.retrieve();
        for (int i = 0; i < fullSet.size(); i += 2) {
            list.add(fullSet.get(i));
        }
        return list;
    }

    public static ArrayList<String> getDirectoryList(String path) {
        //This method fills a list w/ filenames (without the extension) from a directory
        //Used for the admin (checking if a parent course/course already exist or not) and the report
        ArrayList<String> list = new ArrayList<>();
        String[] dirList = new File(path).list();
        for (String file : dirList) {
            //Removing the extension
            list.add(file.replaceFirst("[.][^.]+$", ""));
        }
        return list;
    }
}
