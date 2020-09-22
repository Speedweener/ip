package duke.tasks;

public class Messages {
    private static final String lineSpace = "____________________________________________________________";

    public static final String logo = "______       _        \n"
            + "|  _  \\     | |       \n"
            + "| | | |_   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |/ /| |_| |   <  __/\n"
            +"|___/  \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_WELCOME = "Hello from\n" + logo;

    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise address book application. Exiting...";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";


    //EXCEPTION MESSAGES
    public static final String MESSAGE_IO_INITIALIZE_ERROR = lineSpace + "Unable to create list on your system!"
                                     + "List will not be remembered after the app is ended." + lineSpace;

    public static final String MESSAGE_IO_WRITE_ERROR = "Unable to save changes to local list: ";


    public static final String MESSAGE_FILE_NOT_FOUND = "Existing list not found. Creating new list";


    public static final String MESSAGE_USING_STORAGE_FILE = "Using storage file : %1$s";

}
