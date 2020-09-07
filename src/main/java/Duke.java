import exceptions.IncompleteCommandException;
import exceptions.UnknownCommandException;

import java.util.Scanner;

public class Duke {
    private static final String lineSpace = "____________________________________________________________";

    private static String userInput;
    private static String command;
    private static String details;
    private static String dateTime;

    private static int charIndex;
    private static int taskCount = 0;
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println(lineSpace);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(lineSpace);

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine().trim();

        while(!userInput.equals("bye")) {
            try {
                inputDecider();
            } catch (IncompleteCommandException e) {
                System.out.println("Whoopsie Daisies! You have an empty description for " + command);
                System.out.println("Try again:");
            } catch (UnknownCommandException e) {
                System.out.println("Whoopsie Daisies! You have entered an unknown command!");
                System.out.println("What the heck is \'" + command + "\'?");
                printHelp();
            }
            userInput = in.nextLine().trim();
        }
        System.out.println(lineSpace);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineSpace);
    }

    private static void inputDecider() throws IncompleteCommandException, UnknownCommandException {
            //Split command into 2 separate strings "command" and "details"
            charIndex = userInput.indexOf(' ');
            if(charIndex ==-1){ // Catch for single word input eg "list" or incomplete command
                command = userInput;
                if(command.equals("todo") || command.equals("deadline") || command.equals("event")){
                    throw new IncompleteCommandException();
                }
            } else {
                command = userInput.substring(0, charIndex).trim();
                details = userInput.substring(charIndex +1).trim();
            }

            switch(command){
            case "list":
                list();
                break;
            case "done":
                done();
                break;
            case "todo":
                todo();
                break;
            case "deadline":
                deadline();
                break;
            case "event":
                event();
                break;
            default:
                throw new UnknownCommandException();
            }
    }


    private static void event() {
        charIndex = details.indexOf("/at");
        if(charIndex==-1){ // Catch for invalid event command
            System.out.println("INVALID. No \"/at\" in command");
            return;
        } else {
            dateTime = details.substring(charIndex+3).trim();
            details = details.substring(0, charIndex).trim();
        }
        tasks[taskCount] = new Event(details, dateTime);
        taskCount++;
        addText();
    }

    private static void deadline() {
        charIndex = details.indexOf("/by");
        if(charIndex==-1){ // Catch for invalid deadline command
            System.out.println("INVALID. No \"/by\" in command");
            return;
        } else {
            dateTime = details.substring(charIndex+3).trim();
            details = details.substring(0, charIndex).trim();
        }
        tasks[taskCount] = new Deadline(details, dateTime);
        taskCount++;
        addText();
    }
    public static void printHelp(){
        System.out.println("Available commands are:");
        System.out.println("1) list");
        System.out.println("2) todo");
        System.out.println("3) deadline");
        System.out.println("4) event");
        System.out.println("5) done");
        System.out.println("6) bye");
    }

    public static void list(){
        System.out.println(lineSpace);
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<taskCount; i++) {
            System.out.print((i + 1) + ".");
            tasks[i].printTask();
        }
        if(taskCount==0){
            System.out.println("Empty List");

        }
        System.out.println(lineSpace);
    }


    private static void todo() {
        tasks[taskCount] = new Todo(details);
        taskCount++;
        addText();
    }

    private static void done() {
        if(taskCount == 0){
            System.out.println("No task have been added yet!");
            return;
        }
        int taskNumber = Integer.parseInt(details);
        if(taskNumber<=taskCount){
            if(tasks[taskNumber-1].markAsDone()) { // Returns true if task has not been marked before
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("  ");
                tasks[taskNumber - 1].printTask();
            } else {
                System.out.println("Task has been marked as done already!");
            }
        }  else{
            System.out.println("Invalid \"done\" command!");
        }
    }

    private static void addText(){ // Text to be printed when adding a new task
        System.out.println(lineSpace);
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        tasks[taskCount-1].printTask();
        System.out.println("Now you have " + taskCount + (taskCount<=1? " task ": " tasks ") +"in the list.");
        System.out.println(lineSpace);
    }

    private static void introText(){

    }
}
