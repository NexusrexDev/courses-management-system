import java.io.*;
import java.util.*;

public class FileHandler {
    private String path;
    private File file;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private Scanner fileReader;

    FileHandler(String path) {
        this.path = path;
        file = new File(path);
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean search(String text) {
        while (fileReader.hasNextLine()) {
            String currentLine = fileReader.nextLine();
            if (currentLine.equalsIgnoreCase(text)) {
                return true;
            }
        }
        fileReader.close();
        return false;
    }

    public ArrayList<String> retrieve() {
        ArrayList<String> data = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            data.add(fileReader.nextLine());
        }
        fileReader.close();
        return data;
    }

    public void append(String... text) {
        try {
            for (int i = 0; i < text.length; i++) {
                fileWriter.append(text[i] + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public boolean update(String updatedContent) {
        try {
            fileWriter.write(updatedContent);
            fileWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: cannot update file");
            return false;
        }
    }

    public boolean delete() {
        return file.delete();
    }
}
