import java.util.*;
import java.io.*;

public class ParentCourse implements EventListener {

   private String name;
   private String code;
   private ArrayList<String> courses = new ArrayList<>();
   private FileWriter writer;
   private File file;
   private FileHandler fileHandler;
   ParentCourse(String code, String name,ArrayList<String> courses)
   {
       fileHandler = new FileHandler(Global.ParentCourseFolder + code + ".txt");
       this.code = code;
       this.name = name;
       this.courses = courses;
       update();
   }

   ParentCourse(String code) {
        this.code = code;
        fileHandler = new FileHandler(Global.ParentCourseFolder + code + ".txt");
        read();
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public String getCode() {
        return code;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }


    public void addCourse(String courseID) {
        courses.add(courseID);
        update();
    }

    public boolean removeCourse(String course) {
        //This method removes a course from the array list then updates the file
        if (courses.contains(course)) {
            courses.remove(course);
            update();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update() {
       String data = name + "\n";
       for (String IDs : courses) {
           data = data + IDs + "\n";
       }
       fileHandler.update(data);
    }

    @Override
    public void read() {
        //Retrieve all file data
        ArrayList<String> parentCourseData = fileHandler.retrieve();
        //Name is the first line
        this.name = parentCourseData.get(0);
        //All the remaining lines are course IDs
        for (int i = 1; i < parentCourseData.size(); i++) {
            if (!parentCourseData.get(i).isEmpty())
                courses.add(parentCourseData.get(i));
        }
    }

    @Override
    public void delete() {
        fileHandler.delete();
    }

    public String toString()
    {
        String header = code +" - "+ name +"\n";
        String coursesNo = "Number of courses : " +courses.size()+"\n";
        return header+coursesNo;
    }

}
