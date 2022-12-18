import java.io.*;
import java.util.*;

public class FileHandler {
    private String path;
    private File file;
    private FileWriter fileWriter;
    private Scanner fileReader;

    FileHandler(String path) {
        this.path = path;
    }

    public void create() {
        file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean search(String text) {
        try {
            fileReader = new Scanner(file);
        } catch (Exception e) {}
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
        try {
            fileReader = new Scanner(file);
        } catch (Exception e) {}
        ArrayList<String> data = new ArrayList<>();
        try {
            while (fileReader.hasNextLine()) {
                data.add(fileReader.nextLine());
            }
        } catch (Exception e) {
            fileReader.close();
            System.out.println("Exception");
        }
        finally {
            fileReader.close();
        }
        return data;
    }

    public void append(String... text) {
        try {
            fileWriter = new FileWriter(path, true);
            for (int i = 0; i < text.length; i++) {
                fileWriter.append(text[i] + "\n");
            }
        } catch (IOException e) {
            System.out.println("Cannot append to file");
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ex) {};
        fileWriter = null;
    }

    public boolean update(String updatedContent) {
        try {
            fileWriter = new FileWriter(path);
            fileWriter.write(updatedContent + "\n");
        } catch (Exception e) {
            System.out.println("Error: cannot update file");
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ex) {};
        fileWriter = null;
        return true;
    }

    public boolean delete() {
        try {
            file.delete();
        } catch (Exception e) {
            System.out.println("File is not deleted, surprisingly");
        }
        return true;
    }
}