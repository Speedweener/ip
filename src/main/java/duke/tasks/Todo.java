package duke.tasks;

import duke.tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printTask(){
        System.out.println("[T]" + "[" + this.getStatusIcon() + "] " +this.description);
    }

    @Override
    public String saveString(String details, String dateTime) {
        return System.lineSeparator() + "T | 0 | "
                + details;
    }

    @Override
    public String toString() {
        return  "T | " + (isDone? 1:0) + " | " + description;
    }


}