package duke.userinterface;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.IncompleteCommandException;
import duke.exceptions.UnknownCommandException;
import duke.Commands.*;

public class Parser {

    /** Empty string variable to store detais of the users commands*/
    private static String details = "";


    /**
     * Deciphers the input string and initializes and returns the corresponding
     * Command with the details .
     * @throws IncompleteCommandException If command is valid and details are incomplete
     * @throws UnknownCommandException If command is invalid
     */
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
        case "before":
            return new BeforeCommand(details);
        case "after":
            return new AfterCommand(details);
        case "today":
            return new TodayCommand(details);
        case "delete":
            return new DeleteCommand(details);
        case "find":
            return new FindCommand(details);
        case "bye":
            return new ExitCommand(details);
        default:
            throw new UnknownCommandException(command);
        }
    }


    /**
     * Returns true if command is a valid command
     * Compares input String with each valid non single word command.
     * Returns false otherwise
     */
    private static boolean validCommand(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event")
                || command.equals("find") || command.equals("before") || command.equals("after")
                || command.equals("delete") || command.equals("done")  ;
    }

}
