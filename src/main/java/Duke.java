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
        String[] list = new String[100];
        int listCount = 0;

        while(!line.equals("bye")){
            if(line.equals("list")){
                System.out.println("____________________________________________________________");
                for(int i=0; i<listCount; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                if(listCount==0){
                    System.out.println("Empty List");

                }
                System.out.println("____________________________________________________________");
            } else {
                list[listCount] = line;
                listCount++;
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
