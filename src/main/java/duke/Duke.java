package duke;

import duke.exceptions.IncompleteCommandException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.*;
import java.util.Scanner;

public class Duke {
    private static final String lineSpace = "____________________________________________________________";

    private static String userInput;
    private static String command;
    private static String details;
    private static String dateTime;
    private static String filePath;

    private static int charIndex;
    private static int taskCount = 0;

    private static boolean runDuke = true;

    public static TaskHelper taskHelper = new TaskHelper();


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        taskHelper.loadList();

        System.out.println(lineSpace);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(lineSpace);

        Scanner in = new Scanner(System.in);


        while (runDuke) {
            userInput = in.nextLine().trim();
            try {
                inputDecider();
            } catch (IncompleteCommandException e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
                printHelp();
            } catch (NumberFormatException e) {
                System.out.println("Invalid \"done/delete\" command!");
            }
        }
        exitMessage();
    }

    private static void exitMessage() {
        System.out.println(lineSpace);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineSpace);
    }



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


    public static void printHelp() {
        System.out.println("Available commands are:");
        System.out.println("1) list");
        System.out.println("2) todo");
        System.out.println("3) deadline");
        System.out.println("4) event");
        System.out.println("5) done");
        System.out.println("7) delete");
        System.out.println("8) bye");
    }



}
