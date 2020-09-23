package duke.tasks;

import duke.exceptions.IncompleteCommandException;
import duke.exceptions.UnknownCommandException;
import duke.Commands.*;

public class Parser {

    private static String details = "";

    public static Command parse(String userInput) throws IncompleteCommandException, UnknownCommandException {
        //Split command into 2 separate strings "command" and "details"
        int charIndex = userInput.indexOf(' ');
        String command;
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
            return new ListCommand(details);
        case "help":
            return new HelpCommand(details);
        case "done":
            return new DoneCommand(details);
        case "todo":
            return new TodoCommand(details);
        case "deadline":
            return new DeadlineCommand(details);
        case "event":
            return new EventCommand(details);
        case "delete":
            return new DeleteCommand(details);
        case "bye":
            return new ExitCommand(details);
        default:
            throw new UnknownCommandException(command);
        }
    }

    private static boolean validCommand(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

}
