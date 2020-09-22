package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskHelper {

    private static final String lineSpace = "____________________________________________________________";
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int charIndex;
    private static int taskCount = 0;
    private static String filePath;





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
            TaskHelper.tasks.add(new Todo(parts[2].trim()));
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



    public static void delete(String details) {
        if (taskCount == 0) {
            System.out.println("List is empty!");
            return;
        }
        int taskNumber = Integer.parseInt(details);

        if (taskNumber <= taskCount && !(taskNumber < 0)) {
            System.out.println("Noted. I've removed this task:");
            System.out.print("  ");
            tasks.get(taskNumber - 1).printTask();
            tasks.remove(taskNumber - 1);
            taskCount -= 1;
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            try {
                overwriteList(filePath);
            } catch (IOException e) {
                System.out.println("Unable to save changes to local list: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid \"delete\" command!");
            System.out.println("Only " + taskCount + " task are in the list!");
        }
    }

    public static void done(String details) {
        if (taskCount == 0) {
            System.out.println("List is empty!");
            return;
        }
        int taskNumber = Integer.parseInt(details);

        if (taskNumber <= taskCount && !(taskNumber < 0)) {
            if (tasks.get(taskNumber - 1).markAsDone()) { // Returns true if task has not been marked before
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("  ");
                tasks.get(taskNumber - 1).printTask();
                ;
                try {
                    overwriteList(filePath);
                } catch (IOException e) {
                    System.out.println("Unable to save changes to local list: " + e.getMessage());
                }
            } else {
                System.out.println("Task has been marked as done already!");
            }
        } else {
            System.out.println("Invalid \"done\" command!");
            System.out.println("Only " + taskCount + " task have been added!");
        }
    }



    public static void todo(String details, String dateTime) {
        tasks.add(new Todo(details));
        taskCount++;
        addText();
        try {
            appendList(filePath, tasks.get(taskCount - 1).saveString(details, dateTime));
        } catch (IOException e) {
            System.out.println("Unable to save changes to local list: " + e.getMessage());
        }
    }

    public static void deadline(String details, String dateTime) {
        charIndex = details.indexOf("/by");
        if (charIndex == -1) { // Catch for invalid deadline command
            System.out.println("INVALID. No \"/by\" in command");
            System.out.println("Format is: ");
            System.out.println("deadline {deadline name} /by {deadline time}");
            return;
        } else {
            dateTime = details.substring(charIndex + 3).trim();
            details = details.substring(0, charIndex).trim();

            if (dateTime.isEmpty() || details.isEmpty()) {
                System.out.println("Blank name or time for deadline! Please Retry:");
                return;
            }
        }
        tasks.add(new Deadline(details, dateTime));
        taskCount++;
        addText();
        try {
            appendList(filePath, tasks.get(taskCount - 1).saveString(details, dateTime));
        } catch (IOException e) {
            System.out.println("Unable to save changes to local list: " + e.getMessage());
        }

    }

    public static void event(String details, String dateTime) {
        charIndex = details.indexOf("/at");
        if (charIndex == -1) { // Catch for invalid event command
            System.out.println("INVALID. No \"/at\" in command");
            System.out.println("Format is: ");
            System.out.println("event {event name} /at {event time}");
            return;
        } else {
            dateTime = details.substring(charIndex + 3).trim();
            details = details.substring(0, charIndex).trim();
            if (dateTime.isEmpty() || details.isEmpty()) {
                System.out.println("Blank name or time for event! Please Retry:");
                return;
            }
        }
        tasks.add(new Event(details, dateTime));
        taskCount++;
        addText();
        try {
            appendList(filePath, tasks.get(taskCount - 1).saveString(details, dateTime));
        } catch (IOException e) {
            System.out.println("Unable to save changes to local list: " + e.getMessage());
        }
    }

    private static void addText() { // Text to be printed when adding a new task
        System.out.println(lineSpace);
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        tasks.get(taskCount - 1).printTask();
        System.out.println("Now you have " + taskCount + (taskCount <= 1 ? " task " : " tasks ") + "in the list.");
        System.out.println(lineSpace);
    }

    private static void overwriteList(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(listToString());
        fw.close();
    }

    private static void appendList(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static String listToString() {
        String listString = "";
        for (int i=0; i<taskCount; i++) {
            listString = listString.concat(tasks.get(i).toString() + System.lineSeparator());
        }
        return listString;
    }

    public static void list() {
        System.out.println(lineSpace);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printTask();
        }
        if (taskCount == 0) {
            System.out.println("Empty List");

        }
        System.out.println(lineSpace);
    }

}
