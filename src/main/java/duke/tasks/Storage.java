package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private static String filePath;
    private static int taskCount;

    private static  ArrayList<Task> tasks = new ArrayList<>();

    private static final String lineSpace = "____________________________________________________________";

    public Storage (String filePath, ArrayList<Task> tasks) {
        this.filePath = filePath;
        this.tasks = tasks;
    }
    public static void loadList() {
        File f = new File("data/list.txt");
        filePath = f.getAbsolutePath();
        System.out.println(f.getAbsolutePath());
        System.out.println("Loading previous list on your system . . . ");
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Existing list not found. Creating new list");
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                return;
            } catch (IOException a) {
                System.out.println(lineSpace);
                System.out.println("Unable to create list on your system!");
                System.out.println("List will not be remembered after the app is ended.");
                System.out.println(lineSpace);
            }
        }
        while (s.hasNext()) {
            lineDecipher(s.nextLine());
        }
        TaskHelper.list();
    }


    private static void lineDecipher(String lineData) {
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

}
