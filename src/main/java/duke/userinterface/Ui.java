package duke.userinterface;

import duke.Common.Messages;
import duke.exceptions.EmptyCommandException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    /** Constants used for formatting purposes*/
    private static final String LS  = System.lineSeparator();
    private static final String border = "____________________________________________________________";
    private static final String PREFIX = " ## ";

    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
    }


    /** These methods print starting and end messages*/
    public void printWelcome() {
        printToUser(Messages.MESSAGE_WELCOME);
    }
    public void printGoodbye() {
        printToUser(Messages.MESSAGE_GOODBYE);
    }


    /**
     * Reads for user input.
     * Prints PREFIX and "Awaiting input: " to signal to user to enter an input
     * @throws EmptyCommandException If user input is blank or only space characters
     */
    public String readCommand() throws EmptyCommandException {
        System.out.print(PREFIX + "Awaiting input: ");
        String userInput = in.nextLine().trim();
        Scanner in = new Scanner(System.in);
        if(userInput.isEmpty()) {
            throw new EmptyCommandException();
        }
        return userInput;
    }


    /** TPrints all the available commands for Duke*/
    public void printHelp() {
        printToUser("Available commands are:" + LS
                +" 01) list" + LS
                +" 02) todo" + LS
                +" 03) deadline" + LS
                +" 04) event" + LS
                +" 05) done" + LS
                +" 06) delete" + LS
                +" 07) find" + LS
                +" 08) before" + LS
                +" 09) after" + LS
                +" 10) today" + LS
                +" 11) bye");
    }


    /** Prints all the tasks in the task list*/
    public void printList(String list) {
        printToUser("Here are the tasks in your list: " + LS + list);
    }

    /** Prints a filtered list of tasks with a input condition*/
    public void printFiltered(String list, String condition) {
        printToUser("Here are the tasks in your list that " + condition
                    + LS + list);
    }



    /** Prints an additional exiting message to signal successful exiting from Duke*/
    public void printExit(String ... list) {
        printToUser("EXITING ... ");
    }


    /** Common printing method to output all messages with proper prefixes*/
    public void printToUser(String message) {
        System.out.println(PREFIX + border);
        System.out.println(PREFIX + message.replace("\n", LS + PREFIX));
    }


}
