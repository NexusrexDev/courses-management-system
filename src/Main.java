import java.util.*;

public class Main {
    public static Admin admin;
    public static Student student;
    public static Instructor instructor;
    public static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        int selection = loginSelector();
        int panel = login(selection);
        System.out.println(panel);
    }

    //This method allows the user to select the type of user he/she wants to login as
    public static int loginSelector() {
        boolean error = false;
        System.out.println("Select a login type:");
        printList("Login as an admin", "Login as an instructor", "Login as a student");
        do {
            System.out.print("Enter your selection: ");
            try {
                int selection = input.nextInt();
                if (selection >= 1 && selection <= 3) {
                    return selection;
                } else {
                    System.out.println("Incorrect choice, please try again");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Error: enter an actual number");
                input.next(); //Disregarding the entered letter
                error = true;
            }
        } while (error);
        return -1;
    }

    //Login function, takes a username and password
    public static int login(int selection) {
        boolean error = false;
        do {
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
                error = true;
            }
        } while (error);
        return -1;
    }

    public static void printList(String... list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i+1 + " - " + list[i]);
        }
    }
}
