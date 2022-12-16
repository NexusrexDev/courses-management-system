import java.util.*;

public class Person {
    protected String username ;
    protected String password ;
    private FileHandler fileHandler;

    public boolean login(String Username, String Password,String path) throws NonexistentUserException {
        //Initializing the fileHandler
        fileHandler = new FileHandler(path);
        //Storing all data to compare
        ArrayList<String> data = fileHandler.retrieve();
        for (int i = 0; i < data.size(); i += 2) { //Reading usernames only (0, 2, 4..etc)
            if (data.get(i).equalsIgnoreCase(Username)) { //Comparing username to input
                this.username = data.get(i);
                if (data.get(i+1).equals(Password)) { //Comparing password (username index+1) to input
                    this.password = Password;
                    return true;
                }
            }
        }
        throw new NonexistentUserException();
    }

    public String getUsername()
    {
        return this.username;
    }

}
