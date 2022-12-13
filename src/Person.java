import java.util.*;
import java.io.*;

public class Person {
    protected String username ;
    protected String password ;


    public boolean login(String Username, String Password,String path) throws NonexistentUserException {
        File F = new File(path);
        try {
            Scanner input = new Scanner(F);
            while (input.hasNextLine())
            {
                username = input.nextLine();
                password = input.nextLine();
                if (username.equalsIgnoreCase(Username))
                {
                    return password.equals(Password);
                }
            }
            input.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new NonexistentUserException();
    }

    public String getUsername()
    {
        return this.username;
    }



}
