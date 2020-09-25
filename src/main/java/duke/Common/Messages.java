package duke.Common;

public class Messages {
    private static final String LS = System.lineSeparator();
    private static final String border = "____________________________________________________________";


    public static final String logo = "______       _        \n"
            + "|  _  \\     | |       \n"
            + "| | | |_   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |/ /| |_| |   <  __/\n"
            +"|___/  \\__,_|_|\\_\\___|";

    public static final String MESSAGE_WELCOME = "Hello from\n" + logo;
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_INVALID_DATETIME = "Date or time input is incorrect!" +LS
                                                         + "Please use format \"yyyyMMdd HHmm\"";


    /** Messages for Exceptions*/
    public static final String MESSAGE_IO_INITIALIZE_ERROR = border + "Unable to create list on your system!" + LS
                                     + "List will not be remembered after the app is ended." + border;
    public static final String MESSAGE_IO_WRITE_ERROR = "Unable to save changes to local list: ";
    public static final String MESSAGE_FILE_NOT_FOUND = "Existing list not found. Creating new list";
    public static final String MESSAGE_NUMBER_FORMAT_EXCEPTION = "Details entered was not a number!";

}
