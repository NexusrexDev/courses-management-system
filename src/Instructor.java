import java.util.*;


public class Instructor extends Person implements EventListener{

    private String name, phoneNumber, salary;
    private ArrayList<String> courseID = new ArrayList<>();
    private FileHandler fileHandler;
    private FileHandler loginHandler;


    // 3 constructors
    // one to create, another to show, another empty for logging in.
    public Instructor(){

    }

    public Instructor(String username) {
        //This constructor READS the Instructor file
        this.username=username;
        loginHandler = new FileHandler(Global.InstructorLogin);
        loginHandler.create();
        fileHandler = new FileHandler(Global.InstructorFolder + username + ".txt");
        fileHandler.create();
        this.read();
    }

    public Instructor(String username, String password, String name, String phoneNumber, String salary, ArrayList<String> courseID){
        //This constructor CREATES the instructor file
        this.username=username;
        this.password=password;
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.salary=salary;
        this.courseID= (ArrayList<String>) courseID.clone();
        //Appending the username/password to the login file
        loginHandler = new FileHandler(Global.InstructorLogin);
        loginHandler.create();
        loginHandler.append(username, password);
        fileHandler = new FileHandler(Global.InstructorFolder + username + ".txt");
        fileHandler.create();
        this.update();
    }

    // override login -> call super for the path, if true load.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        update();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        update();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
        update();
    }

    public ArrayList<String> getCourseID() {
        return courseID;
    }

    public boolean login(String username, String password) throws Exception {
        if (super.login(username, password, Global.InstructorLogin )){
            loginHandler = new FileHandler(Global.InstructorLogin);
            loginHandler.create();
            fileHandler = new FileHandler(Global.InstructorFolder + username + ".txt");
            fileHandler.create();
            this.read();
            return true;
        }
        else
            throw new NonexistentUserException();
    }

    // setGrade(String courseID, String userID, String grade) function
    public boolean setGrade(String courseID, String userID, String grade){
        Course c = new Course(courseID);
        if (c.getStudentUsernames().contains(userID)) {
            Student student = new Student(userID);
            student.addGrades(courseID,grade);
            return true;
        }
        else
            return false;
        }

    public void addCourse(String courseID) {
        this.courseID.add(courseID);
        update();
    }

    // remove course
    public boolean removeCourse(String course){
        if (this.courseID.contains(course)){
            courseID.remove(course);
            update();
            return true;
        }
        else return false;
    }

    // read surveys of a course
    public void readSurvey(String course) {
        if (courseID.contains(course)) {
            FileHandler surveyHandler = new FileHandler(Global.SurveyFolder + course + ".txt");
            surveyHandler.create();
            System.out.println("Surveys for " + course + " course: ");
            for (String survey : surveyHandler.retrieve()) {
                System.out.println(survey);
            }
        }
    }

    // override toString
    public String toString(){
        String instructorHeader = username + " - " + name + "\n";
        String instructorPhoneNum = "Phone number: " + phoneNumber + "\n";
        String instructorSalary = "Salary: " + salary + "\n";
        String instructorCourses = "Number of taught courses: " + courseID.size() + "\n";
        return instructorHeader + instructorPhoneNum + instructorSalary + instructorCourses;
    }

    // update function
    @Override
    public void update() {
        String i = name + "\n" + phoneNumber + "\n" + salary;
        for (String courses: courseID){
            i = i + "\n" + courses;
        }
        fileHandler.update(i);
    }

    // delete instructor
    @Override
    public void delete()
    {
        //remove username and password for said instructor
        ArrayList<String> loginData = loginHandler.retrieve();
        if (loginData.contains(username)) {
            loginData.remove(loginData.indexOf(username) + 1);
            loginData.remove(username);
        }
        String data = loginData.toString().replaceAll(", ", "\n");
        data = data.replaceFirst("\\[", "");
        data = data.replaceFirst("]", "");
        loginHandler.update(data);

        // remove the instructor file itself
        fileHandler.delete();
    }

    @Override
    public void read()
    {
        ArrayList<String> data = fileHandler.retrieve();
        this.name = data.get(0);
        this.phoneNumber = data.get(1);
        this.salary = data.get(2);
        if (data.contains(""))
        {
            data.remove("");
        }
        if (data.size() > 3) {
            for (int i = 3; i < data.size(); i++) {
                if (!data.get(i).isEmpty()) {
                    courseID.add(data.get(i));
                }
            }
        }
    }
}

