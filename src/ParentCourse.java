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
           writer = new FileWriter(code +".txt");
           writer.write(name +"\n");
           writer.write(code +"\n");
           for (String cours : courses) writer.write(cours + "\n");
           writer.close();

       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }

   ParentCourse(String code)
   {
        this.code = code;
        read();
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void update() {
        try {
            writer = new FileWriter(code +".txt");
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
        file = new File(code +".txt");
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
        File file = new File(code + ".txt");
        file.delete();
    }
}
