import java.util.*;
import java.io.*;

public class ParentCourse implements EventListener {

   private String name;
   private String code;
   private ArrayList<String> courses = new ArrayList<>();
   private FileWriter writer;
   private File file;



    ParentCourse(String name, String code,ArrayList<String> courses)
   {
       try {

           writer = new FileWriter(Global.ParentCourseFolder + code +".txt");
           writer.write(name +"\n");
           writer.write(code +"\n");
           for (String cours : courses) writer.write(cours + "\n");
           writer.close();

       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }

   ParentCourse(String code) {
        this.code = code;
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

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
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
        try {
            writer = new FileWriter(Global.ParentCourseFolder + code +".txt");
            writer.write(name +"\n");
            writer.write(code +"\n");
            for (String cours : courses) writer.write(cours + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void read() {
        file = new File(Global.ParentCourseFolder + code +".txt");
        try {
            Scanner read = new Scanner(file);
            this.name =read.nextLine();
            String line ;

            while (read.hasNextLine())
            {
                line = read.nextLine();
                if(line.equals(code))
                    continue;
                else courses.add(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }}

    @Override
    public void delete() {
        file = new File(Global.ParentCourseFolder + code +".txt");
        file.delete();
    }

    public String toString()
    {
        String header = code +" - "+ name +"\n";
        String coursesNo = "Number of courses : " +courses.size()+"\n";
        return header+coursesNo;
    }

}
