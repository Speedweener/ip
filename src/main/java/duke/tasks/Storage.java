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


    private void lineDecipher(String lineData, ArrayList<Task> tasks, Ui ui) {
        String[] parts = lineData.split("\\|");
        switch (parts[0].trim()) {
        case "T":
            if (parts.length < 3) {
                ui.printToUser("Invalid Todo task!");
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
                ui.printToUser("Invalid Deadline task!");
                break;
            }
            if (!DateTimeValidator.isValid(parts[3].trim())) {
                ui.printToUser("Invalid date/time for Deadline task!");
                break;
            }
            tasks.add(new Deadline(parts[2].trim(), DateTimeValidator.stringToDateTime(parts[3].trim())));
            if (parts[1].trim().equals("1")) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
            break;
        case "E":
            if (parts.length < 4) {
                ui.printToUser("Invalid Event task!");
                break;
            }
            if (!DateTimeValidator.isValid(parts[3].trim())) {
                ui.printToUser("Invalid date/time for Event task!");
                break;
            }
            tasks.add(new Event(parts[2].trim(), DateTimeValidator.stringToDateTime(parts[3].trim())));
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
