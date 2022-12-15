import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static Admin admin;
    public static Student student;
    public static Instructor instructor;
    public static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        while (true) {
            int selection = loginSelector();
            int panel = login(selection);
            switch (panel) {
                case 1:
                    //Admin panel
                    adminPanel();
                    break;
                case 2:
                    //Instructor panel
                    instructorPanel();
                    break;
                case 3:
                    //Student panel
                    studentPanel();
                    break;
            }
        }
    }

    //This method allows the user to select the type of user he/she wants to login as
    public static int loginSelector() {
        clearConsole();
        System.out.println("Select a login type:");
        printList("Login as an admin", "Login as an instructor", "Login as a student");
        while (true) {
            System.out.print("Enter your selection: ");
            try {
                int selection = input.nextInt();
                if (selection >= 1 && selection <= 3) {
                    return selection;
                } else {
                    System.out.println("Error: Incorrect choice, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
            }
        }
    }

    //Login function, takes a username and password
    public static int login(int selection) {
        clearConsole();
        while (true) {
            System.out.print("Username: ");
            String username = input.next();
            System.out.print("Password: ");
            String password = input.next();
            try {
                switch (selection) {
                    case 1:
                        admin = new Admin();
                        admin.login(username, password);
                        return selection;
                    case 2:
                        instructor = new Instructor();
                        instructor.login(username, password);
                        return selection;
                    case 3:
                        student = new Student();
                        student.login(username, password);
                        return selection;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //Start of admin panel methods
    public static void adminPanel() {
        while (true) {
            clearConsole();
            System.out.println("Welcome, " + admin.getUsername());
            printList("Manage parent courses", "Manage instructors", "Manage students",
                    "Create a new course", "Create a report", "Log out");
            System.out.print("Enter your selection: ");
            try {
                int selection = input.nextInt();
                switch (selection) {
                    case 1:
                        parentCourseMenu();
                        break;
                    case 2:
                        System.out.println("Instructor management");
                        break;
                    case 3:
                        System.out.println("Student management");
                        break;
                    case 4:
                        System.out.println("Course creation");
                        break;
                    case 5:
                        createReport();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Error: Incorrect choice, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
            }
        }
    }

    public static void parentCourseMenu() {
        while (true) {
            clearConsole();
            printList("Add a parent course", "Update a parent course", "Delete a parent course",
                    "View parent courses", "Main panel");
            System.out.print("Enter your selection: ");
            try {
                int selection = input.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("Adding a parent course");
                        break;
                    case 2:
                        System.out.println("Updating a parent course");
                        break;
                    case 3:
                        deleteParentCourse();
                        break;
                    case 4:
                        listParentCourses();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Error: Incorrect choice, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
            }
        }
    }

    public static void createParentCourse() {

    }

    public static void updateParentCourse() {

    }

    public static void deleteParentCourse() {
        ArrayList<String> parentCourses = Global.getDirectoryList(Global.ParentCourseFolder);
        if (parentCourses.size() > 0) {
            System.out.println("Select parent course to delete: ");
            printList(String.valueOf(parentCourses), "Main panel");
            System.out.println("Enter your selection: ");
            try {
                int selection = input.nextInt();
                if (selection >= 1 && selection <= parentCourses.size()) {
                    admin.deleteParentCourse(parentCourses.get(selection - 1));
                    System.out.println("Parent course deleted successfully!");
                    Pause();
                } else if (selection == parentCourses.size() + 1) {
                    return;
                } else {
                    System.out.println("Error: Incorrect choice, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
            }
        } else {
            System.out.println("No parent courses to delete");
            Pause();
        }
    }

    public static void listParentCourses() {
        ArrayList<String> parentCourses = Global.getDirectoryList(Global.ParentCourseFolder);
        System.out.println("Parent courses: " + parentCourses.size());
        for (String parentCourseCode : parentCourses) {
            ParentCourse parentCourse = new ParentCourse(parentCourseCode);
            System.out.println(parentCourse.toString());
        }
        Pause();
    }

    public static void createReport() {
        clearConsole();
        System.out.println("Select report type: ");
        printList("Courses near to start", "Courses near to end");
        while (true) {
            System.out.print("Enter your selection: ");
            try {
                int selection = input.nextInt();
                switch (selection) {
                    case 1:
                        admin.createReport(true);
                        System.out.println("The report is saved in Reports/starting.txt");
                        break;
                    case 2:
                        admin.createReport(false);
                        System.out.println("The report is saved in Reports/ending.txt");
                        break;
                    default:
                        System.out.println("Error: Incorrect choice, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
            }
            Pause();
        }
    }

    public static void instructorPanel()
    {
        while (true) {
            clearConsole();
            System.out.println("Welcome, " + instructor.getName());
            printList("Publish grades", "View courses", "View survey", "Log out");
            System.out.print("Enter your selection: ");
            try {
                int selection = input.nextInt();
                switch (selection) {
                    case 1:
                        publishGrades();
                        break;
                    case 2:
                        viewCourses();
                        Pause();
                        break;
                    case 3:
                        viewSurvey();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Error: Incorrect choice, please try again");
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
            }
        }
    }

    public static void publishGrades()
    {
        ArrayList<String> courses = instructor.getCourseID();
        //list courses
        if(instructor.getCourseID().isEmpty())
        {
            System.out.println("You have zero courses");
        }
        else
        {
            while (true)
            {
                clearConsole();
                System.out.println("Available courses: ");
                printList(courses);
                System.out.println(courses.size() + 1 + " - Main panel");
                System.out.print("Enter your selection: ");

                try
                {
                    int selection = input.nextInt();
                    if(selection >=1 && selection <=instructor.getCourseID().size())
                    {
                        setGrades(instructor.getCourseID().get(selection-1));
                    }
                    else if(selection ==instructor.getCourseID().size()+1) break;
                    else  System.out.println("Incorrect choice, please try again");
                }
                catch (Exception e)
                {
                    System.out.println("Error: enter an actual number");
                    input.next();
                }

            }
        }
    }

    public static void setGrades(String courseID)
    {
        Course course = new Course(courseID);
        for(String student : course.getStudentUsernames())
        {
            boolean errComp = false;
            while (!errComp)
            {
                try {
                    clearConsole();
                    System.out.print("Set grade for " + student + ": ");
                    int grade = input.nextInt();
                    if(grade >=0 && grade<= course.getGrade()) {
                        instructor.setGrade(courseID,student,grade+"");
                        errComp = true;
                    }
                    else
                    {
                        System.out.println("Grade is not in specified range ");
                    }

                }
                catch (Exception e)
                {
                    System.out.println("Error: enter an actual number");
                    input.next();
                }
            }
        }
        System.out.println("All grades are set!");
        Pause();

    }

    public static void viewCourses()
    {
        ArrayList<String> courseIDs = instructor.getCourseID();
        if(courseIDs.isEmpty())
        {
            System.out.println("You have zero courses.");
        }
        else
        {
            System.out.println("Courses number : "+courseIDs.size());
            for(String id : courseIDs)
            {
                Course course = new Course(id);
                System.out.println(course.toString());
            }
        }

    }

    public static void viewSurvey()
    {
        ArrayList<String> courses = instructor.getCourseID();
        if(instructor.getCourseID().isEmpty())
        {
            System.out.println("You have zero courses");
        }
        else
        {
            while (true)
            {
                clearConsole();
                System.out.println("Available courses: ");
                printList(courses);
                System.out.println(courses.size() + 1 + " - Main panel");
                System.out.print("Enter your selection: ");

                try
                {
                    int selection = input.nextInt();
                    if(selection >=1 && selection <=instructor.getCourseID().size())
                    {
                        instructor.readSurvey(instructor.getCourseID().get(selection-1));
                        Pause();
                    }
                    else if(selection ==instructor.getCourseID().size()+1) break;
                    else  System.out.println("Incorrect choice, please try again");
                }
                catch (Exception e)
                {
                    System.out.println("Error: enter an actual number");
                    input.next();
                }

            }
        }

    }

    //Start of student panel methods
    public static void studentPanel() {
        while (true) {
            clearConsole();
            System.out.println("Welcome, " + student.getName());
            printList("View grades", "View courses", "Create survey", "Update information", "Log out");
            System.out.println("Enter your selection: ");
            try {
                int selection = input.nextInt();
                clearConsole();
                switch (selection) {
                    case 1:
                        showGrades();
                        Pause();
                        break;
                    case 2:
                        listCourses();
                        Pause();
                        break;
                    case 3:
                        createSurvey();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Incorrect choice, please try again");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next();
            }
        }
    }

    public static void showGrades() {
        if (!student.getCourses().isEmpty()) {
            System.out.println("Your grades ");
            System.out.println("_____________________");

            for (int i = 0; i < student.getGrades().size(); i++) {
                if (!student.getGrades().get(i).equals(""))
                    System.out.println(student.getCourses().get(i) + " : " + student.getGrades().get(i));
                else System.out.println(student.getCourses().get(i) + " : " + "unset grade");
            }
        } else System.err.println(student.getName() + " do not register any course.");
    }

    public static void listCourses() {
        System.out.println("Your courses ");
        System.out.println("_____________________");
        if (!student.getCourses().isEmpty()) {
            int index = 1;
            for (String course : student.getCourses()) {
                System.out.println(index + "- " + course);
                index++;
            }

        }
    }

    public static void createSurvey() {
        input = new Scanner(System.in);
        boolean err = false;
        do {
            clearConsole();
            listCourses();
            System.out.println(student.getCourses().size() + 1 + "- Main panel");
            System.out.println("Select course number : ");
            try {
                int selection = input.nextInt();
                if (selection >= 1 && selection <= student.getCourses().size()) {
                    System.out.println("Enter your comment : ");
                    String comment = input.next();
                    student.createSurvey(student.getCourses().get(selection - 1), comment);
                    err = true;

                } else if (selection == student.getCourses().size() + 1) {
                    err = false;
                } else {
                    System.out.println("Incorrect choice, please try again");
                    err = true;
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next();
                err = true;
            }
        } while (err);

    }

    public static void updateStudent() {
        input = new Scanner(System.in);
        boolean err = true;
        String newInfo;

        do {
            clearConsole();
            System.out.println("Update panel");
            System.out.println("_____________________");
            printList("Name : " + student.getName(), "Age : " + student.getAge(), "Phone number : " + student.getPhone(),
                    "Main panel");
            System.out.println("Enter your selection : ");
            try {
                int selection = input.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("Enter a new name : ");
                        input.nextLine();
                        newInfo = input.nextLine();
                        student.setName(newInfo);
                        break;
                    case 2:
                        System.out.println("Enter a new age : ");
                        input.nextLine();
                        newInfo = input.nextLine();
                        student.setAge(newInfo);
                        break;
                    case 3:
                        System.out.println("Enter an new phone number : ");
                        input.nextLine();
                        newInfo = input.nextLine();
                        student.setPhone(newInfo);
                        break;
                    case 4:
                        err = false;
                        break;
                    default:
                        System.out.println("Incorrect choice, please try again");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next();
            }
        } while (err);


    }

    //Helper functions
    public static void printList(String... list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i+1 + " - " + list[i]);
        }
    }

    public static void printList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + " - " + list.get(i));
        }
    }

    public static void Pause() {
        Scanner input = new Scanner(System.in);
        System.out.print("Press Enter to return ");
        try {
            input.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole()  {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ignored) {}
    }

}
