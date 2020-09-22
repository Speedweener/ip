package duke.tasks;

public class Ui {

    private static final String LS  = System.lineSeparator();
    private static final String LINE_BORDER  = "##";



    public void showWelcome() {
        printToUser(Messages.MESSAGE_WELCOME);
    }


    public void printToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_BORDER + m.replace("\n", LS + LINE_BORDER));
        }
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
