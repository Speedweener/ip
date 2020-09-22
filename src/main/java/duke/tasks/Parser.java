package duke.tasks;

import duke.exceptions.IncompleteCommandException;
import duke.exceptions.UnknownCommandException;

public class Parser {

    private static String userInput;
    private static String command;
    private static String details;
    private static String dateTime;
    private static String filePath;

    private static int charIndex;
    private static int taskCount = 0;

    private static boolean runDuke = true;

    public static TaskHelper taskHelper = new TaskHelper("data/list.txt");


    private static void inputDecider() throws IncompleteCommandException, UnknownCommandException {
        //Split command into 2 separate strings "command" and "details"
        charIndex = userInput.indexOf(' ');
        if (charIndex == -1) { // Catch for single word input eg "list" or incomplete command
            command = userInput;
            if (validCommand(command)) {
                throw new IncompleteCommandException(command);
            }
        } else {
            command = userInput.substring(0, charIndex).trim();
            details = userInput.substring(charIndex + 1).trim();
        }

        switch (command) {
        case "list":
            taskHelper.list();
            break;
        case "done":
            taskHelper.done(details);
            break;
        case "todo":
            taskHelper.todo(details, "");
            break;
        case "deadline":
            taskHelper.deadline(details, dateTime);
            break;
        case "event":
            taskHelper.event(details, dateTime);
            break;
        case "delete":
            taskHelper.delete(details);
            break;
        case "bye":
            runDuke = false;
            return;
        default:
            throw new UnknownCommandException(command);
        }
    }

    private static boolean validCommand(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

}
