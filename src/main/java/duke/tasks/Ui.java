package duke.tasks;

import duke.exceptions.EmptyCommandException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LS  = System.lineSeparator();
    private static final String border = "____________________________________________________________";

    private static final String PREFIX = " ## ";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }


    public void printWelcome() {
        printToUser(Messages.MESSAGE_WELCOME);
    }

    public void printGoodbye() {
        printToUser(Messages.MESSAGE_GOODBYE);
    }


    public String readCommand() throws EmptyCommandException {
        System.out.print(PREFIX + "Awaiting input: ");
        String userInput = in.nextLine().trim();
        Scanner in = new Scanner(System.in);
        if(userInput.isEmpty()) {
            throw new EmptyCommandException();
        }
        return userInput;
    }

    public void printHelp() {
        printToUser("Available commands are:"
                +"1) list"
                +"2) todo"
                +"3) deadline"
                +"4) event"
                +"5) done"
                +"6) delete"
                +"7) bye");
    }

    public void printList(String list) {
        printToUser("Here are the tasks in your list: " + LS + list);
    }

    public void printFiltered(String list, String condition) {
        printToUser("Here are the tasks in your list that " + condition
                    + LS + list);
    }




    public void printExit(String ... list) {
        printToUser("EXITING ... ");
    }



    public void printToUser(String message) {
        System.out.println(PREFIX + border);
        System.out.println(PREFIX + message.replace("\n", LS + PREFIX));
    }




//    public  void printHelp() {
//        printToUser("Available commands are:");
//        System.out.println("1) list");
//        System.out.println("2) todo");
//        System.out.println("3) deadline");
//        System.out.println("4) event");
//        System.out.println("5) done");
//        System.out.println("7) delete");
//        System.out.println("8) bye");
//    }

}
