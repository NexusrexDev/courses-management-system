public class Person {
    private String username = "";
    private String password = "";

    public boolean Login(String Username, String Password) {
        File F = new File("fileName.txt");
        Scanner input = new Scanner(F);
        username = input.nextLine();
        password = input.nextLine();
        if (username.equals(Username)) {
            if (password.equals(Password)) {
                input.close();
                return true;
            } else {
                return false;
            }
        }
    }

    return false;
}
