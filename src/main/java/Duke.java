import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");


        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int taskCount = 0;
        Task[] tasks = new Task[100];

        while(!line.equals("bye")){
            if(line.equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<taskCount; i++) {
                    System.out.print((i + 1) + ".");
                    tasks[i].printTask();
                }
                if(taskCount==0){
                    System.out.println("Empty List");

                }
                System.out.println("____________________________________________________________");
            } else if(line.indexOf("done") != -1){
                line = line.substring(4).trim();
                int taskNumber = Integer.parseInt(line);
                if(taskNumber<=taskCount){
                    tasks[taskNumber-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.print("  ");
                    tasks[taskNumber-1].printTask();
                }  else{
                    System.out.println("Invalid \"done\" command!");

                }
            } else {
                tasks[taskCount] = new Task(line);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + line);
                System.out.println("____________________________________________________________");
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
