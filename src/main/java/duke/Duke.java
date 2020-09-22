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

    private TaskHelper taskHelper;
    private Ui ui;
    private Storage storage;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskHelper = new TaskHelper(storage.readFromFile(), storage);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/list.txt").run();
    }


//    public static void main(String[] args) {
//
//
//
//
//
//        taskHelper.loadList();
//
//        System.out.println(lineSpace);
//        System.out.println("Hello! I'm Duke");
//        System.out.println("What can I do for you?");
//        System.out.println(lineSpace);
//
//        Scanner in = new Scanner(System.in);
//
//
//        while (runDuke) {
//            userInput = in.nextLine().trim();
//            try {
//                inputDecider();
//            } catch (IncompleteCommandException e) {
//                System.out.println(e.getMessage());
//            } catch (UnknownCommandException e) {
//                System.out.println(e.getMessage());
//                printHelp();
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid \"done/delete\" command!");
//            }
//        }
//        exitMessage();
//    }
//
//    private static void exitMessage() {
//        System.out.println(lineSpace);
//        System.out.println("Bye. Hope to see you again soon!");
//        System.out.println(lineSpace);
//    }


}
