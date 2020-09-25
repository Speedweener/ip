package duke.tasks;
import duke.Common.DateTimeValidator;
import duke.Common.Messages;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class TaskHelper {

    private static final String LS = System.lineSeparator();

    /** List of different tasks */
    private ArrayList<Task> tasks;

    /** Number of tasks in the list*/
    private int taskCount;

    /** Index used to split up the details and dateTime*/
    private int charIndex;

    public TaskHelper (ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskCount = tasks.size();
    }

    /**
     * Checks if input details is valid then deletes the task and returns the confirmation message
     * Else returns the error messages
     * valid = (Positive integer value not higher than taskCount)
     */
    public String delete(String details, Storage storage) {
        if (taskCount == 0) {
            return "List is empty!";
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            return ("Invalid 'delete' command!" + LS + Messages.MESSAGE_NUMBER_FORMAT_EXCEPTION);
        }

        if (taskNumber <= taskCount && !(taskNumber < 0)) {
            String confirmMessage = "Noted. I've removed this task:" + LS
                               + "\t" + tasks.get(taskNumber - 1).toString();
            tasks.remove(taskNumber - 1);
            taskCount -= 1;
            confirmMessage += LS + "Now you have " + taskCount + " tasks in the list.";
            try {
                storage.overwriteList(tasks);
            } catch (IOException e) {
                confirmMessage += LS + Messages.MESSAGE_IO_WRITE_ERROR + e.getMessage();
            }
            return confirmMessage;
        } else {
            return "Invalid \"delete\" command!"
                    + LS + "Only " + taskCount + " task are in the list!";
        }
    }

    /**
     * Checks if input details is valid then marks the task as done and returns the confirmation message
     * Else returns the error message
     * valid = (Positive integer value not higher than taskCount and not marked before)
     */
    public String done(String details, Storage storage) {
        if (taskCount == 0) {
            return ("List is empty!");
        }

        int taskNumber;
        try {
             taskNumber = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            return ("Invalid 'done' command!" + LS + Messages.MESSAGE_NUMBER_FORMAT_EXCEPTION);
        }

        if (taskNumber <= taskCount && !(taskNumber < 0)) {
            if (tasks.get(taskNumber - 1).markAsDone()) { // Returns true if task has not been marked before
                String confirmMessage = "Nice! I've marked this task as done:" + LS
                                       + "\t" + tasks.get(taskNumber - 1).toString();
                try {
                    storage.overwriteList(tasks);
                } catch (IOException e) {
                    confirmMessage += LS + Messages.MESSAGE_IO_WRITE_ERROR + e.getMessage();
                }

                return confirmMessage;
            } else {
                return ("Task has already been marked as done!");
            }
        } else {
            return "Invalid \"done\" command!"
                   + LS + "Only " + taskCount + " task have been added!";
        }
    }


    /** Adds new Todo task and returns the confirmation message */
    public String todo(String details, Storage storage) {
        tasks.add(new Todo(details));
        taskCount++;
        String confirmMessage = addText();
        try {
            storage.appendList(tasks.get(taskCount - 1).exportTask());
        } catch (IOException e) {
            confirmMessage = confirmMessage + Messages.MESSAGE_IO_WRITE_ERROR + e.getMessage();
        }
        return confirmMessage;
    }

    /** Adds new Deadline task and returns the confirmation message */
    public String deadline(String details, Storage storage) {
        /** Splits details into details and dateTime */
        charIndex = details.indexOf("/by");
        String dateTime;
        if (charIndex == -1) { // Catch for invalid deadline command
            return ("INVALID. No \"/by\" in command"
                    + LS +"Format is: "
                    + LS + "deadline {deadline name} /by {deadline yyyyMMdd HHmm}");
        } else {
            dateTime = details.substring(charIndex + 3).trim();
            details = details.substring(0, charIndex).trim();

            if (dateTime.isEmpty() || details.isEmpty()) {
                return("Blank name or time for deadline! Please Retry:");
            }
        }

        /** Verifies that dateTime is valid, else returns error message*/
        if (DateTimeValidator.isValid(dateTime)) {
            tasks.add(new Deadline(details, DateTimeValidator.stringToDateTime(dateTime)));
            taskCount++;
            String confirmMessage = addText();
            try {
                storage.appendList(tasks.get(taskCount - 1).exportTask());
            } catch (IOException e) {
                confirmMessage = confirmMessage + LS + Messages.MESSAGE_IO_WRITE_ERROR  + e.getMessage();
            }
            return confirmMessage;

        }
        return Messages.MESSAGE_INVALID_DATETIME;
    }

    /** Adds new Event task and returns the confirmation message */
    public String event(String details, Storage storage) {
        /** Splits details into details and dateTime */
        charIndex = details.indexOf("/at");
        String dateTime;
        if (charIndex == -1) { // Catch for invalid event command
            return ("INVALID. No \"/at\" in command"
                    + LS +"Format is: "
                    + LS + "event {event name} /at {event time}");
        } else {
            dateTime = details.substring(charIndex + 3).trim();
            details = details.substring(0, charIndex).trim();
            if (dateTime.isEmpty() || details.isEmpty()) {
                return("Blank name or time for event! Please Retry:");
            }
        }

        /** Verifies that dateTime is valid, else returns error message*/
        if (DateTimeValidator.isValid(dateTime)) {
            tasks.add(new Event(details, DateTimeValidator.stringToDateTime(dateTime)));
            taskCount++;
            String confirmMessage = addText();
            try {
                storage.appendList(tasks.get(taskCount - 1).exportTask());
            } catch (IOException e) {
                confirmMessage = confirmMessage + LS + Messages.MESSAGE_IO_WRITE_ERROR  + e.getMessage();
            }
            return confirmMessage;

        }
        return Messages.MESSAGE_INVALID_DATETIME;
    }

    /** Returns text to be printed when adding a new task  */
    private String addText() {
        return("Got it. I've added this task:"
                + LS + "\t" + tasks.get(taskCount - 1).toString()
                + LS + "Now you have " + taskCount +
                (taskCount <= 1 ? " task " : " tasks ") + "in the list.");
    }


    /** Returns the entire list */
    public String list() {
        String list = "";
        if (taskCount == 0) {
            return ("Empty List");
        }
        for (int i = 0; i < taskCount; i++) {
           list += (i + 1) + "." + tasks.get(i).toString() + System.lineSeparator();
        }
        return list;
    }


    /** Returns the task in the list before the input date */
    public String filterBefore(LocalDateTime dateTime) {
        String list = "";
        if (taskCount == 0) {
            return ("Empty List");
        }
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).getDateTime()!=null) { //Filters out Todo which returns a null value
                if (tasks.get(i).getDateTime().isBefore(dateTime)) {
                    list += (i + 1) + "." + tasks.get(i).toString() + System.lineSeparator();
                }
            }
        }

        if(list.isEmpty()) {
            list = "No tasks come before the given date and time!";
        }
        return list;
    }

    /** Returns the task in the list after the input date */
    public String filterAfter(LocalDateTime dateTime) {
        String list = "";
        if (taskCount == 0) {
            return ("Empty List");
        }
        for (int i = 0; i < taskCount; i++) {

            if (tasks.get(i).getDateTime()!=null) { //Filters out Todo which returns a null value
                if (tasks.get(i).getDateTime().isAfter(dateTime)) {
                    list += (i + 1) + "." + tasks.get(i).toString() + System.lineSeparator();
                }
            }
        }

        if(list.isEmpty()) {
            list = "No tasks come after the given date and time!";
        }
        return list;
    }

    /** Returns the task in the list happening on the current date */
    public String filterToday() {
        String list = "";
        if (taskCount == 0) {
            return ("Empty List");
        }
        for (int i = 0; i < taskCount; i++) {

            if (tasks.get(i).getDateTime()!=null) { //Filters out Todo which returns a null value
                if (tasks.get(i).getDateTime().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())) {
                    list += (i + 1) + "." + tasks.get(i).toString() + System.lineSeparator();
                }
            }
        }

        if(list.isEmpty()) {
            list = "No tasks come one the given date!";
        }
        return list;
    }

    /** Returns the task in the list which contain the keyword in the task description */
    public String filterKeyword(String keyword) {
        String list = "";
        if (taskCount == 0) {
            return ("Empty List");
        }
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                list += (i + 1) + "." + tasks.get(i).toString() + System.lineSeparator();
            }
        }
        if(list.isEmpty()) {
            list = "No tasks contain the specified keyword!";
        }
        return list;
    }




}
