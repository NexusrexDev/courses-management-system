public class NonexistentUserException extends Exception {
    public NonexistentUserException()
    {
        super("This user doesn't exist");
    }
}