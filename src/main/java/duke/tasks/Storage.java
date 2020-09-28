package duke.tasks;

import duke.common.DateTimeValidator;
import duke.common.Messages;
import duke.userinterface.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    /** Variables for reading and writing to txt file*/
    private File f;
    private String fullFilePath;


    /** Variables to create tasks from txt file*/
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;



    /** Costants to identify the different tasks in the txt file */
    private final String TODO = "T";
    private final int TODO_LENGTH = 3;

    private final String DEADLINE = "D";
    private final int DEADLINE_LENGTH = 4;

    private final String EVENT = "E";
    private final int EVENT_LENGTH = 4;



    public Storage(String filePath) {
        f = new File(filePath);
        fullFilePath = f.getAbsolutePath();
    }


    /**
     * Reads from txt file and returns an ArrayList<Task> accordingly
     * Txt file is specified from the filePath input during initialization
     * If the txt file does not exist, method will attempt to create a new txt file.
     * Alerts user if unable to create the txt file
     * Uses Ui class to print error messages
     */
    public ArrayList<Task> readFromFile(Ui ui) {
        ui.printToUser("Loading previous list on your system from: " + System.lineSeparator() + fullFilePath);

        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            ui.printToUser(Messages.MESSAGE_FILE_NOT_FOUND);
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
                return new ArrayList<>();
            } catch (IOException a) {
                ui.printToUser(Messages.MESSAGE_IO_INITIALIZE_ERROR);
            }
        }
        while (s.hasNext()) {
            lineDecipher(s.nextLine(), tasks, ui);
        }
        return tasks;
    }


    /**
     * Deciphers the lines from the txt file and adds the task to the list.
     * Checks for invalid tasks (No. of parts < Task length)
     * Checks for invalid entries (Not a ToDo, Deadline or Event)
     * Uses DateTimeValidator class to make sure details are a valid date
     * Uses Ui class to print error messages
     */
    private void lineDecipher(String lineData, ArrayList<Task> tasks, Ui ui) {
        String[] parts = lineData.split("\\|");
        switch (parts[0].trim()) {
        case TODO:
            if (parts.length < TODO_LENGTH) {
                ui.printToUser("Invalid Todo task!");
                break;
            }
            tasks.add(new Todo(parts[TODO_LENGTH-1].trim()));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        case DEADLINE:
            if (parts.length < DEADLINE_LENGTH) {
                ui.printToUser("Invalid Deadline task!");
                break;
            }
            if (!DateTimeValidator.isValid(parts[DEADLINE_LENGTH-1].trim())) {
                ui.printToUser("Invalid date/time for Deadline task!");
                break;
            }
            tasks.add(new Deadline(parts[DEADLINE_LENGTH-2].trim(), DateTimeValidator.stringToDateTime(parts[3].trim())));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        case EVENT:
            if (parts.length < EVENT_LENGTH) {
                ui.printToUser("Invalid Event task!");
                break;
            }
            if (!DateTimeValidator.isValid(parts[EVENT_LENGTH-1].trim())) {
                ui.printToUser("Invalid date/time for Event task!");
                break;
            }
            tasks.add(new Event(parts[EVENT_LENGTH-2].trim(), DateTimeValidator.stringToDateTime(parts[3].trim())));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        default:
            ui.printToUser("INVALID TASK DETECTED!");
            break;
        }
    }

    public void overwriteList(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fullFilePath);
        taskCount = tasks.size();
        fw.write(listToString(tasks));
        fw.close();
    }

    public void appendList(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(fullFilePath, true); // create a FileWriter in append mode
        taskCount = tasks.size();
        fw.write(textToAppend);
        fw.close();
    }

    private String listToString(ArrayList<Task> tasks) {
        String listString = "";
        for (int i=0; i<taskCount; i++) {
            listString = listString.concat(tasks.get(i).exportTask() + System.lineSeparator());
        }
        return listString;
    }
}
