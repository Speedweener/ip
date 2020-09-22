package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private int taskCount = 0;
    private File f;
    private ArrayList<Task> tasks = new ArrayList<>();
    private String fullFilePath;

    public Storage(String filePath) {
        f = new File(filePath);
        fullFilePath = f.getAbsolutePath();
    }

    public ArrayList<Task> readFromFile() {
        System.out.println("Loading previous list on your system from: ");
        System.out.println(fullFilePath);

        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(Messages.MESSAGE_FILE_NOT_FOUND);
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                return new ArrayList<>();
            } catch (IOException a) {
                System.out.println(Messages.MESSAGE_IO_INITIALIZE_ERROR);
            }
        }
        while (s.hasNext()) {
            lineDecipher(s.nextLine(), tasks);
        }
        return tasks;
    }


    private void lineDecipher(String lineData, ArrayList<Task> tasks) {
        String[] parts = lineData.split("\\|");
        switch (parts[0].trim()) {
        case "T":
            if (parts.length < 3) {
                System.out.println("Invalid Todo task!");
                break;
            }
            tasks.add(new Todo(parts[2].trim()));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        case "D":
            if (parts.length < 4) {
                System.out.println("Invalid Deadline task!");
                break;
            }
            tasks.add(new Deadline(parts[2].trim(), parts[3].trim()));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        case "E":
            if (parts.length < 4) {
                System.out.println("Invalid Event task!");
                break;
            }
            tasks.add(new Event(parts[2].trim(), parts[3].trim()));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        default:
            System.out.println("INVALID TASK DETECTED!");
            break;
        }
    }

    public void overwriteList(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fullFilePath);
        fw.write(listToString(tasks));
        fw.close();
    }

    public void appendList(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(fullFilePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private String listToString(ArrayList<Task> tasks) {
        String listString = "";
        for (int i=0; i<taskCount; i++) {
            listString = listString.concat(tasks.get(i).toString() + System.lineSeparator());
        }
        return listString;
    }


}
