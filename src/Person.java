import java.util.*;
import java.io.*;

public class Person {
    protected String username ;
    protected String password ;
    protected   String path;

    public boolean login(String Username, String Password) {
        File F = new File(path);
        try {
            Scanner input = new Scanner(F);
            while (input.hasNextLine())
            {
                username = input.nextLine();
                password = input.nextLine();
                if (username.equals(Username))
                {
                    return password.equals(Password);
                }

            }
            input.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String getUsername()
    {
        return this.username;
    }



}
