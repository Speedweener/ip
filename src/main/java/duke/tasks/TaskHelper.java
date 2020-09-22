package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskHelper {

    private static final String lineSpace = "____________________________________________________________";
    private ArrayList<Task> tasks = new ArrayList<>();
    private int charIndex;
    private int taskCount;
    private String fullFilePath;
    private String pathName;
    private Storage storage;


    public TaskHelper (ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        taskCount = tasks.size();
        this.storage = storage;
        list();
    }

    public void delete(String details) {
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
                storage.overwriteList(tasks);
            } catch (IOException e) {
                System.out.println("Unable to save changes to local list: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid \"delete\" command!");
            System.out.println("Only " + taskCount + " task are in the list!");
        }
    }

    public void done(String details) {
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
                    storage.overwriteList(tasks);
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



    public void todo(String details, String dateTime) {
        tasks.add(new Todo(details));
        taskCount++;
        addText();
        try {
            storage.appendList(tasks.get(taskCount - 1).saveString(details, dateTime));
        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_IO_WRITE_ERROR + e.getMessage());
        }
    }

    public void deadline(String details, String dateTime) {
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
            storage.appendList(tasks.get(taskCount - 1).saveString(details, dateTime));
        } catch (IOException e) {
            System.out.println("Unable to save changes to local list: " + e.getMessage());
        }

    }

    public void event(String details, String dateTime) {
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
            storage.appendList(tasks.get(taskCount - 1).saveString(details, dateTime));
        } catch (IOException e) {
            System.out.println("Unable to save changes to local list: " + e.getMessage());
        }
    }

    private void addText() { // Text to be printed when adding a new task
        System.out.println(lineSpace);
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        tasks.get(taskCount - 1).printTask();
        System.out.println("Now you have " + taskCount + (taskCount <= 1 ? " task " : " tasks ") + "in the list.");
        System.out.println(lineSpace);
    }

    public void list() {
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
