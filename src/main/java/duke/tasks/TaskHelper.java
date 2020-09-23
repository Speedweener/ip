package duke.tasks;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class TaskHelper {

    private static final String LS = System.lineSeparator();

    private ArrayList<Task> tasks;
    private int charIndex;
    private int taskCount;


    public TaskHelper (ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskCount = tasks.size();
    }

    public String delete(String details, Storage storage) {
        if (taskCount == 0) {
            return "List is empty!";
        }
        int taskNumber = Integer.parseInt(details);

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

    public String done(String details, Storage storage) {
        if (taskCount == 0) {
            return ("List is empty!");
        }
        int taskNumber = Integer.parseInt(details);

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
                return ("Task has been marked as done already!");
            }
        } else {
            return "Invalid \"done\" command!"
                   + LS + "Only " + taskCount + " task have been added!";
        }
    }



    public String todo(String details, Storage storage) {
        tasks.add(new Todo(details));
        taskCount++;
        String confirmMessage = addText();
        try {
            storage.appendList(tasks.get(taskCount - 1).saveString(details, ""));
        } catch (IOException e) {
            confirmMessage = confirmMessage + Messages.MESSAGE_IO_WRITE_ERROR + e.getMessage();
        }
        return confirmMessage;
    }

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
                storage.appendList(tasks.get(taskCount - 1).saveString(details, dateTime));
            } catch (IOException e) {
                confirmMessage = confirmMessage + LS + Messages.MESSAGE_IO_WRITE_ERROR  + e.getMessage();
            }
            return confirmMessage;

        }
        return Messages.MESSAGE_INVALID_DATETIME;
    }

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
                storage.appendList(tasks.get(taskCount - 1).saveString(details, dateTime));
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


    /** Returns the list as a string */
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


}
